# Design Description

Hongnan Zhang, hzhang929@gatech.edu

1. ***When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).***

The "userEntryPoint" class stands for the "main menu" that the user is automatically directed to when the app starts. 

It should have 4 buttons with labels and therefore allow the user to utilize all 4 functions mentioned above. 

2. **When choosing to enter current job details, a user will:**  
   1. **Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of:**
      - **Title**
      - **Company**
      - **Location (entered  as city and state)**
      - **Yearly salary adjusted for cost of living**
      - **Yearly bonus \\adjusted for cost of living**
      - **Leave time (in days)**
      - **Number of stock option shares offered**
      - **Home Buying Program fund (one-time dollar amount up to 15% of Yearly Salary)**
      - **Wellness Fund ($0 to $5,000 inclusive annually)**
   2. **Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.**  

To enter/edit the current job, the "jobEditor" class is involved. 

When entering the "enterCurrentJob" for the first time, a new "jobEditor" object is created. All 9 job attributes mentioned above are added to "jobEditor" to get the inputs from the user interface (initially set to null), then 9 attributes are packed into an ArrayList. Both "currentJob" and "jobEditor" can only have one object at a time. 

When the user decides to "save", it stores the attributes ArrayList to "currentJob", which inherits from the abstract class "job". Meanwhile, an "isInit" flag would be set to "true" to indicate that the "currentJob" object is initialized with valid inputs and therefore comparable. Every time clicking on the "enterCurrentJob", "jobEditor" reloads attributes ArrayList from "currentJob" for editing.

On the contrary, when deciding to "cancel", none of the attributes and initial flag would be set in the "currentJob" object and then "exit". 

3. **When choosing to enter job offers, a user will:**
   - **Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.**
   - **Be able to either save the job offer details or cancel.**
   - **Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).**

In "enterJobOffer" section, "jobEditor", "jobComparator" are employed. 

When clicking on "enterJobOffer", a "jobEditor" object receives all 9 attributes inputs from the user interface, and "save" allows it to store the attributes ArrayList to a new "currentOffer" object, which inherits from the "job" class (Different from "currentJob", "currentOffer" can have multiple instances). Each "save" adds the current object to the "offersArray" in "jobEditor", which stores references of all offers entered for now. Likewise, "cancel" leads to nothing created or assigned before "exit". 

When the user decides to "compare the offer",  the "isInit" flag of the "currentJob" object will be checked first, and only if the flag is true the function would be available. Then a "jobComparator" object is created, and the attributes ArrayList from the "currentJob" and newly created "currentOffer" object would be assigned to "comparableAttributes1 and 2". Finally, the "performComparison()" is called and a table containing information from two objects would be presented to the user.

4. **When adjusting the comparison settings,* the user can assign integer weights to:**

   - **Yearly salary**
   - **Yearly bonus**
   - **Leave time**
   - **Number of shares offered**
   - **Home Buying Program Fund**
   - **Wellness Fund**

   **If no weights are assigned, all factors are considered equal.**

For the "comparisonSettings", I added weights-related attributes to "jobeditor" and "job". All weight attributes are initially 1 while the "total weight" is 6. (Weights arrays are set to default correspondingly when "currentJob" and "currentOffer" objects are created. )

When the user enters the "comparison settings", weights inputs are passed to the "jobEditor" object and packed into an array. When the user chooses to "save", all weight attributes are assigned to all "currentJob" and "currentOffer" objects. When the user decides to "cancel", no assignment occurs before exit. 

5. **When choosing to compare job offers, a user will:**

   - **Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.**

   - **Select two jobs to compare and trigger the comparison.**

   - **Be shown a table comparing the two jobs, displaying, for each job:**

     - **Title**
   
     - **Company**
     - **Location**
     - **Yearly salary adjusted for cost of living**
     - **Yearly bonus adjusted for cost of living**
     - **Leave time**
     - **Number of shares offered**
     - **Home Buying Program fund (one-time up to 15% of Yearly Salary)**
     - **Wellness Fund fund ($0 to $5,000 inclusive annually)**
   
   - **Be offered to perform another comparison or go back to the main menu.** 

In this section, "jobComparator" and "jobRanker" objects are involved. 

When the user entered the "compareJobOffers", the length of "offersArray" and the "isInit" flag of the "currentJob" object will be checked to identify whether there is 1 available job/offer at least. If not, this function is currently unavailable. 

When two or more valid jobs are detected, jobs will be ranked by "performRanking()" on their scores, which are automatically calculated when objects are created or "comparisonSettings" is saved. Then the information about jobs (title, company) is presented to users, ranked descendingly on scores. In the case that two or more jobs have the same score, all jobs will share the same rank in parallel. The next job on the score sequence will be ranked with "the previous rank minus the number of jobs in parallel". The rank of each job will also be assigned to "currentJob" and "currentOffer" objects. 

When the user selects two jobs for comparison, the attributes ArrayList of these two jobs will be loaded into the "jobComparator" object and the "performCamparison()" function will be called, and an information table of two comparable jobs will be presented to the user. The user can also click on an "another comparison" button to select new jobs and start a new comparison. 

6. **When ranking jobs, a job’s score is computed as the weighted sum of:** 

   **AYS + AYB + (LT * AYS / 260) + (CSO/2) + HBP + WF**

   **where:** 

   **AYS = yearly salary adjusted for cost of living** 

   **AYB = yearly bonus adjusted for cost of living **

   **LT = leave time** 

   **CSO = Company shares offered (assuming a 2-year vesting schedule and a price-per-share of $1)**

   **HBP = Home Buying Program**

   **WF= Wellness Fund**

After weights-related attributes are set to objects in "comparisonSettings", the "calcuJobScore()" functions in each object are called and the score of each job or offer is calculated and stored. 

Once a "currentJob" or "currentOffer" object is created, there are also scores generated based on their default weight attributes. (1 for each, and 6 for total)

7. **The user interface must be intuitive and responsive.**

This part will be handled entirely within the GUI implementation. 

8. **For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).**

The design only covers the functionality of a single-user offer comparison application, excluding other communication or storage needs.
