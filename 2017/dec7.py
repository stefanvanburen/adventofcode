#!/usr/bin/env python

PART2SOL = 521


def get_score(line):
    sp = line.split(" ")[1].strip()
    s = int(sp.strip("()"))
    return s


def get_leaves(line):
    sp = line.split(" ")
    m = [f.strip().strip(",") for f in sp]
    if len(m) > 3:
        # print(m[3:])
        return [x for x in m[3:]]
    return []


def get_branch(lines, root):
    for x in lines:
        if x.split(" ")[0] == root:
            return x


def weight(lines, root):
    m = get_branch(lines, root)
    w = get_score(m)
    a = get_leaves(m)
    # print(a)
    # print(len(a))
    if len(a) > 0:
        weights = []
        idx = 0
        for l in a:
            weights.append(weight(lines, l))
            w += weights[idx]
            idx += 1
        for y in weights:
            for x in weights:
                if x != y:
                    print(weights)
                    print(get_branch(lines, a[0]))

    return w


if __name__ == "__main__":
    with open("./dec7.txt") as f:
        lines = [l for l in f]
        s = set()
        for x in lines:
            m = get_leaves(x)
            for a in m:
                s.add(a)
        start = None
        for p in lines:
            z = p.split(" ")[0]
            if z not in s:
                start = z
        weight(lines, start)
