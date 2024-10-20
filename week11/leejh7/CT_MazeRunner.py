class Player:
    def __init__(self, idx, r, c):
        self.id = idx
        self.r = r
        self.c = c
        self.dis = 0
        self.leave = False

    def __str__(self):
        return f"참가자{self.id} ({self.r}, {self.c}) {self.leave}"


def calc_distance(r1, r2, c1, c2):
    return abs(r1 - r2) + abs(c1 - c2)


def move_player(player: Player):
    cur_dis = calc_distance(player.r, exit_r, player.c, exit_c)

    for y, x in d4:
        rr = player.r + y
        cc = player.c + x

        if rr < 0 or rr >= N or cc < 0 or cc >= N:
            continue
        if board[rr][cc] > 0:
            continue

        new_dis = calc_distance(rr, exit_r, cc, exit_c)
        if new_dis >= cur_dis:
            continue
        # 출구로 움직임
        if new_dis == 0:
            player.dis += 1
            player.leave = True
            return

        player.dis += 1
        player.r = rr
        player.c = cc
        return


def get_rectangle():
    result = [100, 11, 11]
    for player in players:
        if player.leave:
            continue
        length = max(abs(exit_r - player.r), abs(exit_c - player.c)) + 1
        lr = max(0, min(exit_r, player.r) - (length - abs(exit_r - player.r) - 1))
        lc = max(0, min(exit_c, player.c) - (length - abs(exit_c - player.c) - 1))
        if result[0] > length:
            result = [length, lr, lc]
        elif result[0] == length:
            if result[1] > lr:
                result = [length, lr, lc]
            elif result[1] == lr and result[2] > lc:
                result = [length, lr, lc]

    return result


def rotate_90(sr, er, sc, ec):
    global board
    target = [[0 for _ in range(sc, ec + 1)] for _ in range(sr, er + 1)]

    for i in range(len(target)):
        for j in range(len(target[0])):
            target[i][j] = max(board[i + sr][j + sc] - 1, 0)

    rotate_target = list(zip(*target[::-1]))

    for i in range(len(target)):
        for j in range(len(target[0])):
            board[i + sr][j + sc] = rotate_target[i][j]


def rotate_90_player(sr, er, sc, ec):
    temp_board = [[[] for _ in range(sc, ec + 1)] for _ in range(sr, er + 1)]

    for player in players:
        if player.leave:
            continue
        if sr <= player.r <= er and sc <= player.c <= ec:
            temp_board[player.r - sr][player.c - sc].append(player.id)

    rotate_temp_board = list(zip(*temp_board[::-1]))

    for i in range(len(rotate_temp_board)):
        for j in range(len(rotate_temp_board[0])):
            if len(rotate_temp_board[i][j]) == 0:
                continue
            for rtb in rotate_temp_board[i][j]:
                players[rtb].r = i + sr
                players[rtb].c = j + sc


def rotate_90_exit(sr, er, sc, ec):
    global exit_r, exit_c
    temp_board = [[-1 for _ in range(sc, ec + 1)] for _ in range(sr, er + 1)]

    if sr <= exit_r <= er and sc <= exit_c <= ec:
        temp_board[exit_r - sr][exit_c - sc] = 1

    rotate_temp_board = list(zip(*temp_board[::-1]))

    for i in range(len(rotate_temp_board)):
        for j in range(len(rotate_temp_board[0])):
            if rotate_temp_board[i][j] == -1:
                continue
            exit_r = i + sr
            exit_c = j + sc


def game_over():
    for player in players:
        if not player.leave:
            return False
    return True


def print_board():
    for i in range(N):
        for j in range(N):
            flag = True
            if exit_r == i and exit_c == j:
                print("EX", end=' ')
                continue
            for player in players:
                if player.leave:
                    continue
                if player.r == i and player.c == j:
                    print(f"P{player.id}", end=' ')
                    flag = False
                    break
            if flag:
                print(board[i][j], end=' ')
        print()


d4 = [(-1, 0), (1, 0), (0, -1), (0, 1)]

N, M, K = map(int, input().split())

board = [list(map(int, input().split())) for _ in range(N)]

players = []

for i in range(M):
    r, c = map(int, input().split())
    players.append(Player(i, r - 1, c - 1))

exit_r, exit_c = map(int, input().split())
exit_r -= 1
exit_c -= 1

for k in range(K):
    # 플레이어 이동시키기
    moves = [[] for _ in range(M)]
    for player in players:
        if player.leave:
            continue
        move_player(player)

    if game_over():
        break

    # 출구와 플레이어간 가장 작은 정사각형 만들기
    length, lr, lc = get_rectangle()
    rotate_90(lr, lr + length - 1, lc, lc + length - 1)
    rotate_90_player(lr, lr + length - 1, lc, lc + length - 1)
    rotate_90_exit(lr, lr + length - 1, lc, lc + length - 1)

    # print(f"{k + 1}초후 결과")
    # print_board()
    # print("\n\n")


total = 0
for player in players:
    total += player.dis

print(total)
print(exit_r + 1, exit_c + 1)


