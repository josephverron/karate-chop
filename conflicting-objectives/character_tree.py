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

    def _contains(self, iterator, size: int):
        selection = self._inner.get(next(iterator))
        size -= 1
        while selection and size > 0:
            selection = selection.inner.get(next(iterator))
            size -= 1
        return selection and size > 0

    def __contains__(self, item):
        return self._contains(iter(item), len(item))

    @property
    def inner(self):
        return self._inner


class CharacterTreeEncoder(json.JSONEncoder):

    def default(self, u):

        if isinstance(u, CharacterTree):
            return u.inner
        else:
            type_name = u.__class__.__name__
            raise TypeError("Unexpected type {0}".format(type_name))
