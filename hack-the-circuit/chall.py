import random

def evaluate(x, a, b, c, d, e, f):
	x = False if x == "0" else True
	a = False if a == "0" else True
	b = False if b == "0" else True
	c = False if c == "0" else True
	d = False if d == "0" else True
	e = False if e == "0" else True
	f = False if f == "0" else True
	y = (not ((x and a) or (b ^ c))) and ((d ^ e) or f)
	return y

flag = "havceCTF{d0n'7_f34r_l0g1c_g4t35}"
X = "".join([bin(ord(c))[2:].zfill(8) for c in flag])
A = "".join(["0" if random.random() < 0.5 else "1" for _ in range(len(X))])
B = "".join(["0" if random.random() < 0.5 else "1" for _ in range(len(X))])
C = "".join(["0" if random.random() < 0.5 else "1" for _ in range(len(X))])
D = "".join(["0" if random.random() < 0.5 else "1" for _ in range(len(X))])
E = "".join(["0" if random.random() < 0.5 else "1" for _ in range(len(X))])
F = "".join(["0" if random.random() < 0.5 else "1" for _ in range(len(X))])

print(f"X: {X}")
print(f"A: {A}")
print(f"B: {B}")
print(f"C: {C}")
print(f"D: {D}")
print(f"E: {E}")
print(f"F: {F}")

print("Y: ", end="")
for i in range(len(X)):
	res = "1" if evaluate(X[i], A[i], B[i], C[i], D[i], E[i], F[i]) else "0"
	print(f"{res}", end="")
print()