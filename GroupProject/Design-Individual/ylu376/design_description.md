1. "Main Page" class is the entrance to the app. It includes 4 interface object, corresponding to 4 options from the main page, as current job interface, job offer interface, weight setting interface, and job ranking interface.

2. "Job" class is to store single job details. Hence, it includes all category inputs from title to wellness fund. Meanwhile, for easy control, id input is also created when there are multiple job. For each input, get/set methods are also set.

3. "Setting Weights" class is to store the weights for each category of job detail, from salary weight to wellness fund weight. Corresponding get/set methods also defined.

4. Considering there will be multiple job details stored in the app, while people adding and editing, "Data Base" class is the storage unit to keep information of list of job details and setting weights. It also has input of "currentjobid" used as identifier of current job id from list of all job details. All job-level operation are conducted at "Data Base" class, including "setJobInfo" to add/set job information based on id and category. If id cannot be found, then we create a new Job object, otherwise modify from existing one. Similarly, "setWeights" method is used to add/set "SettingWeights" object. Besides, "computeScore" is to calculate the score of job based on the method provided from doc. And "compareJobs" is to used compared between Job id1 and Job id2.

5. "Current Job Interface" class is to focus on item 2, it has the "DataBase" instance. Based on the boolean value of "save", the information can be saved or disregarded when "exitman()" is called. "editCurrentJob()" is to call "setJobInfo" to edit current job.

6."Job Offer Interface" class is to focus on item 3, it has the "DataBase" instance. Based on the boolean value of "save", the information can be saved or disregarded when "exitman()" is called. "editJobOffer()" is to call "setJobInfo" to edit certain job. "compareOperation" is to call "compareJobs" for job comparing. "showJobOffer" is to generate the summary of job.

7. "weight setting interface" class has instance of "Data Base". "weightSettingOperation" is to call "setWeights" for weight related modification.

8. "Job Ranking Interface" class is for item 5 and 6. Note that, the detailed calculation is done by "DataBase" instance.