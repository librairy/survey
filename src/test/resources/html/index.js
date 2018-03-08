Survey
	.StylesManager
	.applyTheme("default");

var json = {
	"type": "panel",
	"innerIndent": 1,
	"name": "p1AYqmO7hoZT6p#yA_Y68XIJSy",
	"title": "Q1AYqmO7hoZT6p#yA_Y68XIJSy",
	"elements": [{
		"type": "html",
		"name": "h1AYqmO7hoZT6p#yA_Y68XIJSy",
		"html": "<table class='table' >" +
		"<tr>" +
			"<td width='30%' style='text-align:center'><a href='https://www.google.com/search?q=The+Dragon+and+the+Elephant'  target='_blank'>'The Dragon and the Elephant'</a></td><td width='30%' style='text-align:center'><a href='https://www.google.com/search?q=The+China+Strategy'  target='_blank'>'The China Strategy'</a></td>" +
		"</tr>" +
		"<tr>" +
			"<td width='30%' style='text-align:center'><img src='http://static.staging.bluebottlebiz.com/book/bookcover/b9ab77302272373b9727eba886bcfa60eee06438.jpg' width='100px' /></td><td width='30%' style='text-align:center'><img src='http://static.staging.bluebottlebiz.com/book/bookcover/4c67dccc200f7961a3d86e7adb66d75ff115bae7.jpg' width='100px' /></td>" +
		"</tr>" +
		"<tr>" +
			"<td width='30%' style='text-align:center'>The rise of China and India will be the outstanding development of the 21st century, raising fundamental questions about both the structure of the world economy and the balance of global geopolitical power. Will China still be a repressive and undemocratic regime, embracing free market economics but only when it suits? How aggressive a superpower will it be? And what about India, whose huge and growing population and economic prospects appear to guarantee prosperity? David Smith analyses the ways in which the world is tilting rapidly Eastwards, and examines all the implications of the shift in global power to Beijing, Delhi and Washington - a shift that will creep up on us before we know it.</td><td width='30%' style='text-align:center'>No major enterprise or financial institution can avoid doing business with China—if not directly, then through myriad hidden connections. Global businesses either use Chinese resources or sell to and in China or compete with companies that do.</td>" +
		"</tr>" +
		"</table>"
	},
		{
			"type": "radiogroup",
			"name": "1AYqmO7hoZT6p#yA_Y68XIJSy",
			"title": "Are these books related?",
			"isRequired": true,
			"colCount": 1,
			"choices": [
				"yes",
				"no"
			]
		}
	]
};

window.survey = new Survey.Model(json);

survey
	.onComplete
	.add(function (result) {
		document
			.querySelector('#surveyResult')
			.innerHTML = "result: " + JSON.stringify(result.data);
	});

$("#surveyElement").Survey({model: survey});