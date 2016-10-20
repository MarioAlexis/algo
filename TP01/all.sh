#!/bin/bash

bash tp.sh -all merge 1000
bash tp.sh -all merge 5000
bash tp.sh -all merge 10000
bash tp.sh -all merge 50000
bash tp.sh -all merge 100000
bash tp.sh -all merge 500000

bash tp.sh -all mergeSeuil 1000
bash tp.sh -all mergeSeuil 5000
bash tp.sh -all mergeSeuil 10000
bash tp.sh -all mergeSeuil 50000
bash tp.sh -all mergeSeuil 100000
bash tp.sh -all mergeSeuil 500000

bash tp.sh -all bucket 1000
bash tp.sh -all bucket 5000
bash tp.sh -all bucket 10000
bash tp.sh -all bucket 50000
bash tp.sh -all bucket 100000
bash tp.sh -all bucket 500000

bash tp.sh -all bucketSeuil 1000
bash tp.sh -all bucketSeuil 5000
bash tp.sh -all bucketSeuil 10000
bash tp.sh -all bucketSeuil 50000
bash tp.sh -all bucketSeuil 100000
bash tp.sh -all bucketSeuil 500000
