import json


class CharacterTree:

    def __init__(self, initial_value=None):
        self._inner = dict()
        if initial_value:
            self.append(initial_value)

    def append(self, iterable):
        if len(iterable) == 0:
            return

        if iterable[0] in self._inner:
            self._inner[iterable[0]].append(iterable[1:])
        else:
            self._inner[iterable[0]] = CharacterTree(iterable[1:])

    def __repr__(self):
        return json.dumps(self, cls=CharacterTreeEncoder, indent=2)

    def __str__(self):
        return json.dumps(self._inner, cls=CharacterTreeEncoder, indent=2)

    @property
    def inner(self):
        return self._inner

    def contains(self, word):
        if len(word) == 0:
            return True
        elif word[0] in self._inner:
            return self._inner[word[0]].contains(word[1:])
        else:
            return False


class CharacterTreeEncoder(json.JSONEncoder):

    def default(self, u):

        if isinstance(u, CharacterTree):
            return u.inner
        else:
            type_name = u.__class__.__name__
            raise TypeError("Unexpected type {0}".format(type_name))
