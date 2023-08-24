# Design Description

## Requirements
1. The Main Menu(class representation) will present the user with the ability to:  
    - add/enter the current job info
      - the `isInit` attribute of the Current Job class will default to false
      - once the user adds and saves the current job info the value will be set to true
      - the UI will reflect Add Current Job if false else Edit Current Job if true
      - the Current Job class is an instance of the Job Info class
      - an attribute of `id` is included in the Job Info Class for easier control
      - initially there will be no current job and the maximum is 1 current job
      - the Job Manager class will store this value
    - enter job offers
      - initially there will be no job offers but there can be many job offers
      - job offers are represented as a list of type Job Info
      - the Job Manager class will store this value
    - adjust the comparison settings
      - the Job Info Weight class represents integer weights to which can be modified
      - minimum value is 1(or undefined)
      - integer values can be used more than once
      - the Job Manager class will store this instance as `jobWeights`
    - compare job offers
      - the Current Job `isInit` attribute and the `jobOffers` length will be used to enable/disable the compare job offers button
2. Enter Current Job
    - the Job Manager class contains an attribute called `currentJob` which is an instance of the Job Info class
      - if the value is not undefined then the fields will be populated with the contents of such `currentJob` info
        - the ability to edit the fields will be present in the UI 
    - the `currentJob` attribute is only updated if the save button is clicked
    - `isInit` will be set to true in the event `currentJob` was initially undefined
3. Enter Job Offers
    - the Job Manager class contains an attribute called `jobOffers` which is a list containing instances of the Job Info class
    - when the save button is clicked, the current job offer which is an instance of the Job Info class is added to the `jobOffers` list (`addJobOffer(JobInfo)`)
4. Adjust the Comparison Settings
    - the Job Info Weight class represents integers weights to which can be modified
    - additional clarification is needed on the requirements of the minimum number of empty values if any at all
      - the current assumption is that if the field is empty it's corresponding counterpart within the ranking algorithm will not be utilized
      - if all fields are undefined or if all fields contain the same integer value, all factors are considered equal and the ranking algorithm is either calculated without the weight or 1/9 (further clarification needed)
      - if there is a tie, the order will be sorted alphabetically by company name
    - clicking the save button will update the corresponding attributes within the Job Info Weight class for which fields were modified
5. Compare Job Offers
    - the `currentJob` value if not empty will be shown at the top of the page
    - the `jobOffers` list will be used to list out the saved job offers based on their ranking(discussed later)
    - 2 job info instances will be able to be compared (`triggerComparision(jobA, jobB)`)
6. Ranking Jobs
    - the application has a Ranking Algorithm class
    - this algorithm is used by the Job Manager and Job Info Weight to produce the job offer rankings
    - the `computeJobRankScore()` function takes in a current job or job offer(an instance of Job Info)
    - `setJobRankScore()` function sets the `jobRankScore` attribute of the Job Info instance
    - based on the value of the job rank score, the list of jobs will be ordered in decreasing order
7. The user interface must be intuitive and responsive.
    - This requirement will be satisfied by the GUI implementation.
8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
    - additionaly since this is a single user system no login feature will be implemented