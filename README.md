# Flight Search
Helps find cheap flights across multiple flight search engines

# Introduction
Flight search was born out of my frustration of how cumborsome flight search is. You have to do many mouse clicks to set up your airports and dates of travel. And more clicks to check closer airports and other dates. And then if you come back tomorrow you have to do all this clicking again. This project is an attempt to automate all the clicks and get you results in real time.

# How it works
You define a json file with your flight information. The tool then performs all the necessary searches of flights based on the json file. You can just review the results and pick the best flight. Once you have your json file generated you don't need to do anything but run it. This way you can get results at any time. No more clicking with the mouse.

# Primary purpose
Save on clicking. This tool eliminates all clicking to find a flight. Just define a simple json and run it as many times as you want to search for flights.

# Secondary purpose
Find the cheapest possible combination of airports and dates for your trip. Around me I have 4 airports I can use: PHL, EWR, BWI and IAD. Lets say I'm also flexible with my dates: I can depart on Aug 9, 10 or 11 and get back on Sep 9, 10 or 11. Now lets say I'm travelling to Bulgaria which has two airports too: VAR and SOF. Ok, how many combinations exist between all airports and travel dates? What is the chaepest combination? This tool can answer this question. Without any annoying clicking of the mouse.

# Usage
You can look at the help by supplying the -h argument to run.sh:
```
usage: FlightSearch
 -f,--file <FILE>                         The JSON file that contains your
                                          search configuration.
 -g,--generate-urls                       Generate the URLs only without
                                          executing them.
 -h,--help                                Print this message.
 -n,--passangers <NUMBER OF PASSANGERS>   The number of passangers to
                                          search for. The default is 1.
 -p,--paginate <NUMBER OF PAGES>          The number of pages in a page.
                                          When that number of pages has
                                          been opened the user will be
                                          prompted to press enter in order
                                          to continue with the next page.
                                          The default number is 50 pages.
 -s,--site <SITE>                         The site you want to search.
                                          Currently supported sites are:
                                          Kayak, Google, Momondo, Hipmunk,
                                          FlightHub.
 -t,--sleep-time <TIME TO SLEEP>          The number of seconds to sleep
                                          between each opened URL in
                                          miliseconds. The default is 800
                                          miliseconds.
```

Define a JSON file for your flight. Lets call it test.json Here is an example:
```json
[
    {
        "depart": {
            "origins": [
                "EWR",
                "PHL",
                "BWI",
                "IAD"
            ],
            "destinations": [
                "MUC"
            ],
            "dates": [
                {
                    "day": 21,
                    "month": 12,
                    "year": 2019
                }
            ]
        },
        "returning": {
            "origins": [
                "VIE"
            ],
            "destinations": [
                "PHL",
                "EWR",
                "BWI",
                "IAD"
            ],
            "dates": [
                {
                    "day": 26,
                    "month": 1,
                    "year": 2020
                }
            ]
        }
    }
]
```


Now lets run our permutation algorithm to see how many combinations exist:
```
./run.sh -g -f src/main/resources/test-2020.json -s momondo

> Task :flightsearch:run
Trip:
 TravelInfo:
 [EWR, PHL]
 [MUC]
 [FlightDate: 2019-12-21, FlightDate: 2019-12-22]


 TravelInfo:
 [VIE]
 [PHL, EWR]
 [FlightDate: 2020-01-26, FlightDate: 2020-01-27]



Number of total Multi City URLs: 16
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=PHL&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=PHL&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=PHL&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=PHL&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=EWR&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=EWR&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=EWR&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=EWR&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=PHL&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=PHL&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=PHL&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=PHL&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=EWR&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=EWR&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=EWR&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=EWR&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=EWR&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=PHL&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=PHL&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=PHL&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=PHL&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=EWR&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=EWR&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=EWR&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=21-12-2019&SO1=VIE&SD1=EWR&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=PHL&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=PHL&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=PHL&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=PHL&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=EWR&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=EWR&SDP1=26-01-2020&AD=1&TK=ECO&DO=false&NA=false
Generated the URL: http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=EWR&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MUC&SDP0=22-12-2019&SO1=VIE&SD1=EWR&SDP1=27-01-2020&AD=1&TK=ECO&DO=false&NA=false
```

We see that we generated 16 permutations. The website we used is momondo.com. We generated 16 URLs that basically setup the appropriate combination for searching. In order to actually run the searches you can just remove the -g option and the default browser on your operating system will run all of the searches.
```bash
./run.sh -f src/main/resources/test-2020.json -s momondo
```

At this point you're done. You just did 16 flight searches with all of your important combinations and you didn't have to click a mouse once. Go through the results in your browser and find the best flight.
