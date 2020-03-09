# IAMHuman
JSON human command to Selenium driver

## Description
IAMHuman is basically a Selenium wrapper with an extended and simplified JSON command description.

A given JSON input file will be parsed to corresponding Selenium commands.

e.g.:  

<pre><code>
{  
  "targetUrl": ">targetURL<",  
  "actions": [  
    {  
      "selectType": "XPATH", <b>// Type of selector</b>  
      "selector": "//*[@name=\"firstname\"]", <b>// Selector content</b>  
      "action": "TYPE",  <b>// Action type e.g.: TYPE means the targeted element will receive a keyboard input</b> 
      "value": "Ruediger"  <b>// Value for key input. Also possible: randompw(>pw length<) or randomnumber(>from<->to<)</b>
    }  
  ]  
}  
    </code></pre>

A list of all possible commands will follow.

## Status
Work in progress
