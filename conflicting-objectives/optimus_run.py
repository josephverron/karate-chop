from itertools import product


class CharacterTree:

    def __init__(self, initial_value=None):
        self._inner = dict()
        if initial_value:
            self.append(initial_value)

    def append(self, iterable):
        if len(iterable) == 0:
            return

        if iterable[0] in self._inner:
            self._inner[word[0]].append(word[1:])
        else:
            self._inner[word[0]] = CharacterTree(word[1:])

    def __repr__(self):
        return str(self)

    def __str__(self):
        return str(self._inner)


shortlist = {
    1: CharacterTree(),
    2: CharacterTree(),
    3: CharacterTree(),
    4: CharacterTree(),
    5: CharacterTree(),
    6: CharacterTree()
}


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
