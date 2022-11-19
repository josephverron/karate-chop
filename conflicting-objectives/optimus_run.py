from character_tree import CharacterTree

shortlist = {
    1: CharacterTree(),
    2: CharacterTree(),
    3: CharacterTree(),
    4: CharacterTree(),
    5: CharacterTree(),
    6: CharacterTree(),
    7: CharacterTree(),
    8: CharacterTree(),
    9: CharacterTree()
}

targets = []

with open('wordlist.txt', 'r') as dic:
    for line in dic.readlines():
        word = line.strip()
        if len(word) < 10:
            shortlist[len(word)].append(word.strip())
        elif len(word) == 10:
            targets.append(word)


results = []

for leftSize, rightSize in zip(range(1, 10), reversed(range(1, 10))):
    for word in targets:
        word_left = word[:leftSize]
        word_right = word[leftSize:]
        if word_left in shortlist[leftSize] and word_right in shortlist[rightSize]:
            results.append((word_left, word_right, word))
