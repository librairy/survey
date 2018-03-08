package org.librairy.survey.dao;

import com.datastax.driver.core.*;
import io.swagger.model.Answer;
import io.swagger.model.AnswerList;
import io.swagger.model.QuestionCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
@Component
public class AnswerDao extends AbstractDao {

    private static final Logger LOG = LoggerFactory.getLogger(AnswerDao.class);

    @Autowired
    UserCounterDao userCounterDao;

    @Autowired
    QuestionCounterDao questionCounterDao;

    @Override
    public boolean initialize(Session session) {
        session.execute("create table if not exists answers(user text, question text, value text, time text, primary key ((user),question)) WITH gc_grace_seconds = '3600' and compaction = { 'class' :  'LeveledCompactionStrategy'  } and compression = { 'sstable_compression' : '' } and caching = { 'keys' : 'ALL', 'rows_per_partition' : '120' };");
        return false;
    }


    public boolean save(Answer answer){
        LOG.info("Saving answer: " + answer);

        boolean alreadyExists = exists(answer);

        PreparedStatement statement = database.getSession().prepare("insert into answers (user, question, value, time) values (?, ?, ?, ?)");
        database.getSession().executeAsync(statement.bind(answer.getUser(), answer.getQuestion(), answer.getResult(), answer.getTime()));

        if (!alreadyExists){
            // increment question counter
            userCounterDao.increment(answer.getUser());

            // increment user counter
            questionCounterDao.increment(answer.getQuestion());
        }

        LOG.info("Answer '"+answer.getQuestion()+"' saved");
        return true;
    }


    public AnswerList listOf(Integer num, Optional<String> page){
        Statement statement = new SimpleStatement("select user, question, value, time from answers;");
        statement.setFetchSize(num);
        if(page.isPresent()){
            statement.setPagingState(PagingState.fromString(page.get()));
        }

        ResultSet result = database.getSession().execute(statement);
        PagingState pagingState = result.getExecutionInfo().getPagingState();
        List<Row> rows = result.all();

        if (rows == null || rows.isEmpty()) return new AnswerList(Collections.emptyList(),pagingState!= null? pagingState.toString() : "");

        List<Answer> values = rows.stream().limit(num).map(row -> answerFrom(row)).collect(Collectors.toList());

        return new AnswerList(values,pagingState!= null? pagingState.toString() : "");
    }



    public boolean exists(Answer answer){
        PreparedStatement statement = database.getSession().prepare("select time from answers where user = ? and question = ?");
        ResultSet result = database.getSession().execute(statement.bind(answer.getUser(), answer.getQuestion()));
        return result.one() != null;

    }

    private Answer answerFrom(Row row){
        Answer answer = new Answer();
        answer.setUser(row.getString(0));
        answer.setQuestion(row.getString(1));
        answer.setResult(row.getString(2));
        answer.setTime(row.getString(3));
        answer.setId(answer.getUser()+"-"+answer.getQuestion());
        return answer;
    }
}
