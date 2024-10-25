class Knight:
    def __init__(self, idx, r, c, h, w, k):
        self.id = idx
        self.r = r
        self.c = c
        self.h = h
        self.w = w
        self.k = k
        self.live = True
        self.dmg = 0

    def __str__(self):
        return f"기사{self.id} ({self.r}, {self.c})"


def knight_range(knight: Knight, rr, cc):
    result = []
    start_r = knight.r + rr
    start_c = knight.c + cc
    end_r = knight.r + knight.h + rr - 1
    end_c = knight.c + knight.w + cc - 1

    for row in range(start_r, end_r + 1):
        for col in range(start_c, end_c + 1):
            result.append((row, col))

    return result


def move_knight(knight: Knight, dir):
    global is_move
    rr = d4[dir][0]
    cc = d4[dir][1]

    k_range = knight_range(knight, rr, cc)

    # 현재 병사가 이동 가능한지 체크
    for r, c in k_range:
        if r < 0 or r >= L or c < 0 or c >= L:
            return False
        if board[r][c] == 2:
            return False

    # 밀리는 병사가 있는지 체크
    push_k = []
    for kn in ks:
        if not kn.live:
            continue
        if kn.id == knight.id:
            continue
        kn_range = knight_range(kn, 0, 0)
        for pos in k_range:
            if pos in kn_range:
                push_k.append(kn)
                break

    for kn in push_k:
        if not move_knight(kn, dir):
            return False

    is_move.add(knight)
    return True


def get_damage(knight: Knight):
    k_range = knight_range(knight, 0, 0)
    for r, c in k_range:
        if board[r][c] == 1:
            knight.dmg += 1
            knight.k -= 1
            if knight.k <= 0:
                knight.live = False
                return


d4 = [(-1, 0), (0, 1), (1, 0), (0, -1)]

L, N, Q = map(int, input().split())

board = [list(map(int, input().split())) for _ in range(L)]
ks = []

for i in range(N):
    r, c, h, w, k = map(int, input().split())
    ks.append(Knight(i, r - 1, c - 1, h, w, k))


for _ in range(Q):
    is_move = set()
    i, d = map(int, input().split())
    target_k = ks[i-1]

    if not target_k.live:
        continue

    if move_knight(target_k, d):
        for k_m in is_move:
            k_m.r += d4[d][0]
            k_m.c += d4[d][1]
            if k_m.id == target_k.id:
                continue
            get_damage(k_m)

result = 0
for k in ks:
    if k.live:
        result += k.dmg

print(result)