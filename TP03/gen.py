from random import randint
from random import random
import sys

nbGroups     = int(sys.argv[1])
maxGroupSize = int(sys.argv[2])
p            = float(sys.argv[3]) # Probabilité qu'il y ait un poid entre deux groupes de compagnies

nbTable = nbGroups * (1+maxGroupSize)/2 / randint(6,10)
nbTable = max(3, int(nbTable))

print(nbTable)
print(nbGroups)

# Nombre de personnes dans chaque compagnie
for _ in range(0, nbGroups):
    print(randint(1, maxGroupSize)) # newline

impossible_meme_table = []
souhaite_meme_table = []
eviter_meme_table = []
# Chaque pair de compagnies a une probabilité p d'avoir un poid assigné.
for i in range(0, nbGroups):
    for j in range(i+1, nbGroups):
        if random() < p:
            categorie = randint(1,3)
            if categorie == 1:
                impossible_meme_table.append([i,j])
            elif categorie == 2:
                souhaite_meme_table.append([i,j])
            else:
                eviter_meme_table.append([i,j])


for set in [impossible_meme_table, souhaite_meme_table, eviter_meme_table]:
    print(len(set))
    for pair in set:
        print(str(pair[0]) + ' ' + str(pair[1]))
