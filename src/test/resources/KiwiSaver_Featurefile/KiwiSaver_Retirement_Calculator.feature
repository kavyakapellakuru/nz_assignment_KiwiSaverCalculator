Feature: Westpac KiwiSaver Retirement Calculator

#To verify
#User is able to find Information icons for every field in KiwiSaver Retirement Calculator 
#User is displayed current age field information text on clicking the information icon

 @Test_KiwiSaver
 Scenario: SC01_KiwiSaver Retirement Calculator_InformationIcons_CurrentAgeFieldText_Verification
 Given User launches 'Westpac NZ' application
 And Navigates to 'KiwiSaver Retirement Calculator' screen
 Then Verify the display of Information icons for all fields
 And Validate Information text for Current Age field

 #To verify
 #User is able to calculate KiwiSaver Projected Balances at Retirement
 #for three different sets of input data passed from excel sheet
 
 @Test_KiwiSaver
 Scenario Outline: <TestCase>_KiwiSaver Retirement Calculator_CalculationOfRetirementBalances
 Given User launches 'Westpac NZ' application
 And Navigates to 'KiwiSaver Retirement Calculator' screen
 Then Verify User is able to calculate projected balances at retirement
 
 
 Examples:
 |TestCase|
 |SC02_A|
 |SC02_B|
 |SC02_C|
 