def group_by(key, seq, container=set, adder=lambda c, v: c.add(v)):
    from functools import reduce
    from collections import defaultdict
    return reduce(lambda grp, val: adder(grp[key(val)], val) or grp, seq, defaultdict(container))


def all_splits_exists(word, sub_words_size_split, words_buckets):
    start_index = 0
    for split in sub_words_size_split:
        if split not in words_buckets:
            return False
        if word[start_index: start_index + split] not in words_buckets[split]:
            return False
        start_index += split
    return True


def find_sub_words(targets, words_buckets, sub_words_size_splitter):
    return [
        (sub_words_size_split, word)
        for word in targets
        for sub_words_size_split in sub_words_size_splitter(word)
        if all_splits_exists(word, sub_words_size_split, words_buckets)
    ]


def strip_line(line: str):
    return line.strip()


def all_pairs(w):
    return zip(
        range(1, len(w)),
        reversed(range(1, len(w)))
    )


def all_combinations_of_min_size(min_size):
    def all_combinations(_word):
        word_length = len(_word)
        if word_length <= min_size:
            return [tuple([min_size])]
        else:
            return [
                (i, *combination)
                for i in range(min_size, word_length)
                for combination in all_combinations(_word[i:])
            ]

    return all_combinations


if __name__ == '__main__':
    with open('wordlist.txt', 'r') as wordlist_file:
        word_list = map(strip_line, wordlist_file)
        words_by_length = group_by(len, word_list)

    target_words = words_by_length[15]
    tuple_giver = all_combinations_of_min_size(4)
    results = find_sub_words(target_words, words_by_length, tuple_giver)
    for result in enumerate(results):
        print(result)
