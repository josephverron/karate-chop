from itertools import product

shortlist = {1: [], 2: [], 3: [], 4: [], 5: [], 6: []}

with open('wordlist.txt', 'r') as dic:
    for line in dic.readlines():
        word = line.strip()
        if len(word) < 7:
            shortlist[len(word)].append(word.strip())

results = []

for leftSize, rightSize in zip(range(1, 6), reversed(range(1, 6))):
    for left, right in product(shortlist[leftSize], shortlist[rightSize]):
        if left + right in shortlist[6]:
            results.append((left, right))
