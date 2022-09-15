### sample

Sample of past assignment

Affine.java - Implementation of affine cipher

Encryption formula:
> C = aM + b (mod 26)

where C = cipher text, M = plain text, a and b = keys

Key tested: 3, 9

Compile: `javac Affine.java`  
Run: `java Affine -key 3 9 -e <Name of input text file> <Name of output text file>`

Note: 
1. Input file needs to be created beforehand to run
2. Replace `-e` with `-d` to run the decryption function
