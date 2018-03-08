package io.swagger.model;

import java.util.List;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
public class AnswerList {

    private List<Answer> answers;

    private String nextPage;

    public AnswerList(List<Answer> answers, String nextPage) {
        this.answers = answers;
        this.nextPage = nextPage;
    }

    public AnswerList() {
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }
}
