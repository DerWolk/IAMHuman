# IAMHuman
JSON human command to Selenium driver

## Description
IAMHuman is basically a Selenium wrapper with an extended and simplified JSON command description.

A given JSON input file will be parsed to corresponding Selenium commands.

e.g.:  
{  
  "targetUrl": "<targetURL>",  
  "actions": [  
    {  
      "selectType": "XPATH", **// Type of selector**  
      "selector": "//*[@name=\"firstname\"]", **// Selector content**  
      "action": "TYPE",  **// Action type e.g.: TYPE means the targeted element will receive a keyboard input** 
      "value": "Ruediger"  **// Value for key input. Also possible: randompw(<pw length>) or randomnumber(<from>-<to>)**
    }  
  ]  
}  

A list of all possible commands will follow.

## Status
Work in progress
