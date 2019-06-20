#!/usr/bin/env python


PART1 = 23588
PART2 = 10045


def parse_group_root(group):
    return parse_group(group, 1)[0]


def parse_group(group, level):
    idx = 0
    # print("At level %d" % level)
    # print("Current state: %s" % group)
    value = level
    in_garbage = False
    if group == "":
        return value, 0
    while True:
        idx += 1
        v = group[idx]
        # print("At value %s" % v)
        if v == '!':
            idx += 1
            continue
        if not in_garbage:
            if v == '}':
                # print("Returning %d" % value)
                return value, idx
            elif v == '<':
                in_garbage = True
            elif v == '{':
                o, i = parse_group(group[idx:], level + 1)
                # print("Group value: %s" % o)
                # print("Indexes forward: %s" % i)
                value += o
                idx += i
                # print("VALUE NOW: %d" % value)
                continue
        else:
            if v == '>':
                in_garbage = False


def count_garbage(inp):
    idx = 0
    count = 0
    in_garbage = False
    while True:
        if idx >= len(inp):
            return count
        v = inp[idx]
        if v == '!':
            idx += 2
            continue
        if not in_garbage:
            if v == '<':
                in_garbage = True
        else:
            if v == '>':
                in_garbage = False
            else:
                count += 1
        idx += 1


def part1_tests():
    tests = {
        '{}': 1,
        '{{{}}}': 6,
        '{{},{}}': 5,
        '{{{},{},{{}}}}': 16,
        '{<a>,<a>,<a>,<a>}': 1,
        '{{<ab>},{<ab>},{<ab>},{<ab>}}': 9,
        '{{<!!>},{<!!>},{<!!>},{<!!>}}': 9,
        '{{<a!>},{<a!>},{<a!>},{<ab>}}': 3,
    }
    for t in tests:
        print(t)
        out = parse_group_root(t)
        print(out)
        print(tests[t])
        print(out == tests[t])


def part1():
    # part1_tests()
    with open('./dec9.txt') as f:
        return parse_group_root(f.readline())


# 5323 - too high
def part2():
    # part2_tests()
    with open('./dec9.txt') as f:
        return count_garbage(f.readline())


def part2_tests():
    tests = {
        '<>': 0,
        '<random characters>': 17,
        '<<<<>': 3,
        '<{!>}>': 2,
        '<!!>': 0,
        '<!!!>>': 0,
        '<{o"i!a,<{i<a>': 10,
    }
    for t in tests:
        print(t)
        out = count_garbage(t)
        print(out)
        print(tests[t])
        print(out == tests[t])


if __name__ == '__main__':
    print(part1() == PART1)
    print(part2() == PART2)
