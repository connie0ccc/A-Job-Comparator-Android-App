# Fall22Team092 Job Comparator App Quick Demo  
## 1. Dependency
* Android API level: 33.
* Demo runs on a Pixel 4 XL emulator.  
* Class diagram  

  <img src=".\GroupProject\Docs\images\ClassDiagramUpdatedAtD4.png" width="%"> 

## 2. Main page
Main page consists of entrances of four functions: Enter current job, Enter job offers, Adjust comparison settings, Compare job offers.  

<img src=".\GroupProject\Docs\images\demoMainMenu.png" width="30%"> 

User need at least 2 saved current job or offers to initiate ranking and comparing.  

<img src=".\GroupProject\Docs\images\demoMainMenuAtLeast2Jobs.png" width="30%">  


## 3. Enter current job
User can enter and save detials of the current job in this page.  

<img src=".\GroupProject\Docs\images\demoEnteringCurrentJob.png" width="30%">  

Previously saved attributes will be automatically loaded next time visiting this page, and data persists between runs.  

<img src=".\GroupProject\Docs\images\demoEnterCurrentJobUpdated.png" width="30%">  

## 4. Enter job offers
User can enter and save details of a job offer. Multiple offers can be added and saved, data persists between runs.  

<img src=".\GroupProject\Docs\images\demoEnteringOffer.png" width="30%">  

If current editing offer has been saved, comparison with current job can be initiated. Then comparison result will be displayed, user is allowed to initiate new comparison or directly return to main menu at this page.  

<img src=".\GroupProject\Docs\images\demoCompareWithCurrentJob.png" width="30%">  

## 5. Adjust comparison settings
User can edit comparison weights for each attributes used in scoring jobs. Default weights (all 1) are presented to user at the first entrance.  

<img src=".\GroupProject\Docs\images\demoDefaultWeight.png" width="30%">  

If edited weights are saved, they will be automatically loaded next time visiting this page, and data persists between runs.  

<img src=".\GroupProject\Docs\images\demoUpdateWeights.png" width="30%">  

## 6. Compare job offers
User will be presented a list of ranked job and offers in descending order. Scores will be calculated and shown in front of each job item with previously edited comparison weights.(All weights are 1 if never modified). Current job will be indicated if exists.  

<img src=".\GroupProject\Docs\images\demoCompareJobsWithDefaultWeight.png" width="30%">  

Once comparison weights are modified, scores will be recalculated when user entry this page again.  

<img src=".\GroupProject\Docs\images\demoCompareWithUpdatedWeights.png" width="30%">   

User can click on each job item to select 2 of them for the comparison.  

<img src=".\GroupProject\Docs\images\demoCheckBoxSelected.png" width="30%">   

Then comparison result will be displayed, user is allowed to initiate new comparison or directly return to main menu at this page. 

<img src=".\GroupProject\Docs\images\demoComparisonResult.png" width="30%">  