package com.BeerBattle.BeerBattle;

import java.io.IOException;
import java.net.URL;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Menu;
import android.widget.*;
import android.view.View;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.*;
import org.jsoup.select.Elements;

public class MainActivity extends Activity {

	TextView theTitle;
	TextView theTitle2;
	TextView firstBeerName;
	TextView firstBA;
	TextView BARating1;
	TextView firstRB;
	TextView RBRating1;
	TextView AVG1TEXT;
	TextView AVG1;
	TextView secondBeerName;
	TextView secondBA;
	TextView BARating2;
	TextView secondRB;
	TextView RBRating2;
	TextView AVG2TEXT;
	TextView AVG2;
	EditText beerEnter1;
	EditText beerEnter2;
	Button compareBeerButton;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		theTitle = (TextView) findViewById(R.id.the_title);
		theTitle2 = (TextView) findViewById(R.id.the_title2);
		// ratingsTextView = (TextView) findViewById(R.id.ratingsTextView);
		beerEnter1 = (EditText) findViewById(R.id.beerEnter1);
		beerEnter2 = (EditText) findViewById(R.id.beerEnter2);
		compareBeerButton = (Button) findViewById(R.id.getRatings);
		firstBeerName = (TextView) findViewById(R.id.firstBeerName);
		firstBA = (TextView) findViewById(R.id.firstBA);
		BARating1 = (TextView) findViewById(R.id.BARating1);
		firstRB = (TextView) findViewById(R.id.firstRB);
		RBRating1 = (TextView) findViewById(R.id.RBRating1);
		AVG1TEXT = (TextView) findViewById(R.id.AVG1TEXT);
		AVG1 = (TextView) findViewById(R.id.AVG1);
		secondBeerName = (TextView) findViewById(R.id.secondBeerName);
		secondBA = (TextView) findViewById(R.id.secondBA);
		BARating2 = (TextView) findViewById(R.id.BARating2);
		RBRating2 = (TextView) findViewById(R.id.RBRating2);
		secondRB = (TextView) findViewById(R.id.secondRB);
		AVG2TEXT = (TextView) findViewById(R.id.AVG2TEXT);
		AVG2 = (TextView) findViewById(R.id.AVG2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;

	}

	private String[] getRatingRB(String str) throws IOException {
		String queryItem = str;
		String theName = "null";
		Document doc = Jsoup.connect("http://www.ratebeer.com/findbeer.asp")
				.timeout(10000).data("BeerName", queryItem)
				.userAgent("Mozilla").post();
		// Document doc2 =
		// Jsoup.connect("http://www.ratebeer.com").data("BeerName",
		// "Pabst").userAgent("Mozilla").post();
		/*
		 * System.out.println(doc.body().text());
		 * System.out.println(doc2.body().text());
		 * System.out.println("\n\n\n\n\n\n\n\n\n");
		 * System.out.println(doc2.body().text().equals(doc.body().text()));
		 */
		Element content = doc.getElementById("container");
		// Element content2 = doc2.getElementById("container");
		// System.out.println("About to split text..");
		String[] contentStrings = content.text().trim().split("\\s+");
		// System.out.println("Split text");
		// System.out.println("contentStrings length is: "+contentStrings.length);
		int highestRating = 0;
		int toBeReturned = 0;
		for (int i = 0; i < contentStrings.length - 3; i++) {
			// System.out.println(contentStrings[i]);
			if (contentStrings[i].equals("rate")
					&& isInteger(contentStrings[i + 1])
					&& isInteger(contentStrings[i + 2])) {
				// System.out.println("int to be parsed.. "+contentStrings[i+2]);
				// if(isInteger(contentStrings[i+2]
				int parsedInt = Integer.parseInt(contentStrings[i + 2]);
				System.out.println("parsedInt: " + parsedInt);
				if (parsedInt > highestRating) {
					theName = contentStrings[i - 1];
					highestRating = parsedInt;
					toBeReturned = Integer.parseInt(contentStrings[i + 1]);
					System.out.println("new highest rating.. " + highestRating);
				}
			}
		}

		// System.out.println(content.text());
		// System.out.println("\n\n\n\n\n\n\n\n\n\n");
		// System.out.println(content2.text());
		String[] returnList = { theName, Integer.toString(toBeReturned) };
		return returnList;
	}

	private String[] getRatingBA(String str) throws IOException {
		String toReturn = "";
		String theName = "null";
		int parseTimeOut = 3000;
		String queryItem = str;
		System.out.println("queryItem: " + queryItem);
		URL myUrl = new URL("http://www.beeradvocate.com/search/?q="
				+ queryItem.trim());
		Document doc = Jsoup.parse(myUrl, 10000);
		System.out.println("passed pasrse");
		// Element body = doc.select("#loginBar");
		System.out.println("got body..");
		Elements links = doc.select("a");
		// get the div the title is in..
		theName = doc.select("div.titleBar").text();
		System.out.println("\n\ntheName = " + theName + "\n\n");
		// now get the text of the h1 in the div
		// this text ought to be the text for the beer.

		System.out.println("got tags");
		System.out.println("link text: " + links.size());
		for (Element link : links) {
			System.out.println("in loop..");
			System.out.println("link.txt: " + link.text());
			if (link.text().contains(queryItem)) {
				System.out.println("in if..");
				toReturn = link.attr("href");
				theName = link.text();
				System.out.println("toReturn from if.. " + toReturn);
				break;
			}
		}
		System.out.println("getting next stuff..");
		URL myUrl2 = new URL("http://www.beeradvocate.com" + toReturn);
		Document newDoc = Jsoup.parse(myUrl2, 10000);
		Elements newSpans = newDoc.getElementsByTag("span");
		Elements newDivs = newDoc.getElementsByTag("div");
		System.out.println("got body again..");
		// String score = newDoc.select("span.BAscore_big ba-score").first()
		// .text();
		String score = "null";
		for (Element aSpan : newSpans) {
			System.out.println("aSpan text: " + aSpan.text());
			if (aSpan.attr("class").equals("BAscore_big ba-score")) {
				score = aSpan.text();
				System.out.println("inside aSpan if");
			}
		}
		System.out.println("Score: " + score);
		// for(Element el : newDivs.select("h1")){
		// if(el.attr("class").equals("titleBar")){
		// System.out.println("The name.. "+el.text());
		// theName = el.text();
		// }
		// }
		String[] returnList = { theName, score };
		// ratingsTextView.setText("BA: " + score.toString());
		return returnList;
	}

	// use onPause method when users click on links and exit out of app..
	public void onClick(View v) {
		firstBeerName.setText("Processing...");
		secondBeerName.setText("");
		BARating1.setText("");
		
		RBRating1.setText("");
		
		AVG1.setText("");
		
		BARating2.setText("");
		RBRating2.setText("");
		
		AVG2.setText("");
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String beer1 = beerEnter1.getText().toString();
					String beer2 = beerEnter2.getText().toString();
					final String[] theScore = getRatingBA(beer1);
					System.out.println("got getRatingBA(beer1);");
					final String[] theScore2 = getRatingRB(beer1);
					System.out.println("got getRatingRB(beer1);");
					final String[] theScore3 = getRatingBA(beer2);
					System.out.println("got getRatingBA(beer2);");
					final String[] theScore4 = getRatingRB(beer2);
					System.out.println("got getRatingRB(beer2);");
					mHandler.postDelayed(new Runnable() {
						@Override
						public void run() {
							int[] scores = new int[4];
							try {
								scores[0] = Integer.parseInt(theScore[1]);
							} catch (Exception e) {
								scores[0] = 0;
							}
							try {
								scores[1] = Integer.parseInt(theScore2[1]);
							} catch (Exception e) {
								scores[1] = 0;
							}
							try {
								scores[2] = Integer.parseInt(theScore3[1]);
							} catch (Exception e) {
								scores[2] = 0;
							}
							try {
								scores[3] = Integer.parseInt(theScore4[1]);
							} catch (Exception e) {
								scores[3] = 0;
							}
							/*
							 * ratingsTextView.setText(theScore[0]+"\n\nBA: "+
							 * theScore[1]+"  RB: "+theScore2[1]+ " Avg: "+
							 * (scores[0]+scores[1])/2+
							 * "\n\n"+theScore3[0]+"\n\nBA: "
							 * +theScore3[1]+"  RB: "+theScore4[1] +
							 * "   Avg: "+(scores[2]+scores[3])/2 );
							 */
							firstBeerName.setText("\n"+theScore[0]+"\n");
							secondBeerName.setText("\n"+theScore3[0]+"\n");
							// firstBA
							try {
								BARating1.setText(Integer.toString(scores[0]));
								if (scores[0] < 75) {
									BARating1.setTextColor(Color.RED);
								} else if (scores[0] < 90) {
									BARating1.setTextColor(Color.rgb(255, 153, 0));
								} else {
									BARating1.setTextColor(Color.GREEN);
								}

							} catch (Exception e) {
								BARating1.setText("Exception");
							}
							// firstRB;
							try {
								RBRating1.setText(Integer.toString(scores[1]));
								if (scores[1] < 75) {
									RBRating1.setTextColor(Color.RED);
								} else if (scores[1] < 90) {
									RBRating1.setTextColor(Color.rgb(255, 153, 0));
								} else {
									RBRating1.setTextColor(Color.GREEN);
								}
							} catch (Exception e) {
								RBRating1.setText("Exception");
							}
							// TextView AVG1TEXT;
							try {
								int avg = (scores[0] + scores[1]) / 2;
								AVG1.setText(Integer.toString(avg));
								if (avg < 75) {
									AVG1.setTextColor(Color.RED);
								} else if (avg < 90) {
									AVG1.setTextColor(Color.rgb(255, 153, 0));
								} else {
									AVG1.setTextColor(Color.GREEN);
								}

							} catch (Exception e) {
								AVG1.setText("Exception");
							}
							// TextView secondBA
							try {
								BARating2.setText(Integer.toString(scores[2]));
								if (scores[2] < 75) {
									BARating2.setTextColor(Color.RED);
								} else if (scores[2] < 90) {
									BARating2.setTextColor(Color.rgb(255, 153, 0));
								} else {
									BARating2.setTextColor(Color.GREEN);
								}
							} catch (Exception e) {
								BARating2.setText("Exception");
							}
							// TextView secondRB
							try {
								RBRating2.setText(Integer.toString(scores[3]));
								if (scores[3] < 75) {
									RBRating2.setTextColor(Color.RED);
								} else if (scores[3] < 90) {
									RBRating2.setTextColor(Color.rgb(255, 153, 0));
								} else {
									RBRating2.setTextColor(Color.GREEN);
								}
							} catch (Exception e) {
								RBRating2.setText("Exception in rbrating2");
							}
							// TextView AVG2TEXT;
							try {
								int avg = (scores[2] + scores[3]) / 2;
								AVG2.setText(Integer.toString(avg));
								if (avg < 75) {
									AVG2.setTextColor(Color.RED);
								} else if (avg < 90) {
									
									AVG2.setTextColor(Color.rgb(255, 153, 0));
								} else {
									AVG2.setTextColor(Color.GREEN);
								}
							} catch (Exception e) {
								AVG2.setText("Exception");
							}

						}
					}, 2000);
					// System.out.println(getBTC());
					// ratingsTextView.setText("BA: "+getRatingBA());
				} catch (Exception e) {
					System.out.println("Error in getRatingBA()");
					finish();
				}
			}
		});
		thread.start();
		// call some other function.

	}

}
