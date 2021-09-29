# eval-aut-code-doc
Repository for the bachelor's thesis:\
__Evaluation Of Automated Code Documentation Approaches__
  
  
  ---
  ### Preprocessing
  The preprocessing procedure can be invoked by using preprocess_main.py. This file triggers preprocessing procedures sequentially. 
  
  ---
  ### Training 
  The training has to be invoked seperately for each approach. Details on how to train each models are available at the authors repositories:
  [attnFc](https://github.com/Attn-to-FC/Attn-to-FC), [code2seq](https://github.com/tech-srl/code2seq), [CodeGNN](https://github.com/acleclair/ICPC2020_GNN), [NeuralLSPS](https://github.com/mcmillco/funcom)
  
  Some training procedures require additional files than can be easily found at the author's repository for download.
  
  ---
  ### Evaluation
  All approaches can be evaluated sequentially with evaluate_main.py. This requires the preprocessed data and the prediction files from models.
  
  ---
  
Link to download final model files for each approach: [HERE](https://drive.google.com/file/d/1WEvbBdL-52UlHX89TXMZjP5UGle3rxyy/view?usp=sharing) 

Note: Some hardcoded paths require adjustment to fit a different directory structure
