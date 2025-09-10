# COMC 2472 Java/C/C++ Programming II
All of my assignments for Fall 2025 For my Java/C++/C# class

# Exercize 1A: Gregorian / Julian Date translator
These methods allows for future use of a more streamlined dating system for computers, along with translating it back into an easier to understand dating system for humans. This code also allows you to generally get more information about a date in the Gregorian system.

In the Gregorian calander it may be 2025 September 10, however in the Julian calandar that would be 2460929. One's easier to read and remember, while the other is easier for computers to interpret.
- Added ability to translate dates
- Added constructor and comparison functions
    - Constructor:
      - Default Constructor (Janurary 1st 2000): 
        - GDate(): 
      - DMY Constructor: 
        - GDate(Int(year),Int(Month, Int(Day)):
      - Copy Constructor:  
        - GDate(GDate):
      - Julian Day Constructor 
        - GDate(Int)
    - Methods:
      - Copy Method 
        - .copy(GDate);)
      - Equals Method 
        - .equals(GDate):
      - Greater Than Method 
        - .greaterThan(GDate):
          - Used both for less than and greater than operations.
      - Difference Method 
        - .Diff(GDate)
      - Year Method 
        - .Year(GDate)
      - Month Method 
        - .Month(GDate)
      - Day Method 
        - .Day(GDate)
      - Julian Output Method 
        - .julianDay(GDate)
