# eval-aut-code-doc
Repository for the bachelor's thesis:\
Evaluation Of Automated Code Documentation Approaches
\
create a new branch before you implement new approaches...
\
\
FRAMEWORK DESIGN:\
dataset -> pipeline of preprocessing (approach-dependent) -> pipeline of training models -> testing -> evaluate results against golden standard
\
\
\
\
NOTES: \
code2seq: 
preprocessing.py:
- if Java version not compatible with precompiled JavaExtractor no log errors are shown - check stderr during debugging...
- also a problem is that the TMP variable is made global, but during parallel execution globals are not shared... (i used the sequential version - maybe later fix and use parralele again? )
- my hardcoded paths will become a problem later on for others...
\
\
Hi Minxing! 
