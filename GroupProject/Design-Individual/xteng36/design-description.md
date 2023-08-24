# Requirements

1. When the app is started, the user is presented with the main menu, which allows the
   user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison
   settings, or (4) compare job offers (disabled if no job offers were entered yet).

   To realize the requirement, "app" class is made. An attribute used to enable/disable job offer comparison function. If there is job offer, comparison function will be enabled, which will be an attribute of this class.

   

2. When choosing to enter current job details, a user will:
   a. Be shown a user interface to enter (if it is the first time) or edit all the details of
   their current job, which consist of:

   

   | No.   |                                                              |
   | ----- | ------------------------------------------------------------ |
   | i.    | Title                                                        |
   | ii.   | Company                                                      |
   | iii.  | Location                                                     |
   | iv.   | Yearly salary adjusted for cost of living                    |
   | v.    | Yearly bonus adjusted for cost of living                     |
   | vi.   | Leave time                                                   |
   | vii.  | Number of shares offered                                     |
   | viii. | Home Buying Program fund (one-time up to 15% of Yearly Salary) |
   | ix.   | Wellness Fund fund ($0 to $5,000 inclusive annually)         |

   

   b. Be able to either save the job details or cancel and exit without saving, returning
   in both cases to the main menu.

I used a "job" class to save the details of the current. Two sub classes will be used, one is the current job, another is job offer. Current job could be empty if he has no job now. 

3. When choosing to enter job offers, a user will:

   a. Be shown a user interface to enter all the details of the offer, which are the same
   ones listed above for the current job.

   b. Be able to either save the job offer details or cancel.

   c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

   

This is not included in the current design, and it will be included in the future within the GUI implementation.



4. When adjusting the comparison settings, the user can assign integer weights to:
   a. Yearly salary
   b. Yearly bonus
   c. Leave time
   d. Number of shares offered
   e. Home Buying Program Fund
   f. Wellness Fund
   If no weights are assigned, all factors are considered equal.

I used a "weight" class to save the comparison setting, with default value as 1/6 for each factors.

Also, it could be customized by the user. 



4. When choosing to compare job offers, a user will:
   a. Be shown a list of job offers, displayed as Title and Company, ranked from best
   to worst (see below for details), and including the current job (if present), clearly
   indicated.
   b. Select two jobs to compare and trigger the comparison.
   c. Be shown a table comparing the two jobs, displaying, for each job:

   | No.   |                                                              |
   | ----- | ------------------------------------------------------------ |
   | i.    | Title                                                        |
   | ii.   | Company                                                      |
   | iii.  | Location                                                     |
   | iv.   | Yearly salary adjusted for cost of living                    |
   | v.    | Yearly bonus adjusted for cost of living                     |
   | vi.   | Leave time                                                   |
   | vii.  | Number of shares offered                                     |
   | viii. | Home Buying Program fund (one-time up to 15% of Yearly Salary) |
   | ix.   | Wellness Fund fund ($0 to $5,000 inclusive annually)         |

   

   d. Be offered to perform another comparison or go back to the main menu.



I used a "job" class to save the details of the job offer. "Weight" class will rank the job offers and the current job (if present). "d" is not included in the current design, and it will be included in the future within the GUI implementation.



6. When ranking jobs, a job’s score is computed.



"Weight "served as an class, it ranks the jobs based on the weight calculated based on the comparison setting. 







7. The user interface must be intuitive and responsive.



8. For simplicity, you may assume there is a single system running the app (no
   communication or saving between devices is necessary)  



7 and 8 will be considered in the future GUI design. 