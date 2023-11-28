flag = 'havceCTF{g00d_j0b!_th4t_w4s_y0ur_f1r5t_fl4g_ch3ck3r}'

for i in range(len(flag)-1):
    print(ord(flag[i]) + ord(flag[i+1]), end=", ")