#PriceCalcByTime
it is a small program, web based, using JS as a logic part and HTML as the GUI. its job is to provide both an interface that allows the user to create a time structure, and an algorithm that creates a table containing the price for the data given in reference of their time and its value in the time structure.

##Brief Introduction To The Engineering
The app offers two services :
1. Create TOU File : a TOU file is JSON file that contains the data of the time structure and how every time period is divided. being said , this part offers a GUI that takes user data and process them and save them in the JSON file that the user had the ability to downloading and using.
2. Generate Price Table : this part asks the user to give both a TOU file and a CSV File, the TOU file is being used as a reference for price by date and time, and the CSV File contains the data of the value (ex: energy or volume...) and its its date. before generating the program asks the user to design from the valid data in csv the date and value.
Therefore the app does not need to score any data its role being to createor modify a TOU file and process the data of a TOU and CSV.

##How To Use
This app needs nothing other than a browser, by downloading the files you can run them from everywhere using a browser, this being said the app is portable and cross-plateform.

##Developer Note
This work was inspired by the energy companies and their need to find price for the energy consumed in each speacial period of time. What I have Done is create a program that can handle the time-value-price relation, my version is more or less basic, yet upgradable depending on the needs. I used HTML and JS being very light and only require a browser that exists in almost every computer. I am not a Web Developer(Front End , Back End , Full Stack or whatever) , therefore my code is not perfect due to my lack of experience in these languages, and yet it did not require too much energy to become flexible with these material.

##Leave Us A Feedback