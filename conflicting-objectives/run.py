from time import perf_counter

start = perf_counter()

shortlist = {
    1: [],
    2: [],
    3: [],
    4: [],
    5: []
}

targets = []

with open('wordlist.txt', 'r') as dic:
    for line in dic.readlines():
        word = line.strip()
        if len(word) < 6:
            shortlist[len(word)].append(word.strip())
        elif len(word) == 6:
            targets.append(word)

read_dic = perf_counter()

results = []

for leftSize, rightSize in zip(range(1, 6), reversed(range(1, 6))):
    for word in targets:
        word_left = word[:leftSize]
        word_right = word[leftSize:]
        if word_left in shortlist[leftSize] and word_right in shortlist[rightSize]:
            results.append((word_left, word_right, word))

computed_results = perf_counter()

print(f"Found results in {computed_results - start:0.4f} seconds, with {computed_results - read_dic:0.4f} after reading dictionary")
