import rstr
import random
import re

flag_list = []

flag_regex = r'^havceCTF{[a-zA-Z0-9]{5}_[A-Z]{2}_[a-z0-25-9]{7}_[a-ep-zA-LO-Z0-9]{10}}$'
flag_str = rstr.xeger(flag_regex)
flag_list.append(flag_str)

for _ in range(1000):
    extra_regex = r'^[havceCTF]{8}{[a-zA-Z0-9]{5}_[A-Z]{2}_[a-z0-25-9]{7}_[a-ep-zA-LO-Z0-9]{10}}$'
    extra_str = rstr.xeger(extra_regex)
    if(re.findall(flag_regex, extra_str) == []):
        flag_list.append(extra_str)

    extra_regex = r'^[havceCTF]{8}{[a-zA-Z0-9_]{27}}$'
    extra_str = rstr.xeger(extra_regex)
    if(re.findall(flag_regex, extra_str) == []):
        flag_list.append(extra_str)

    extra_regex = r'^havceCTF{[a-zA-Z0-9_]{27}}$'
    extra_str = rstr.xeger(extra_regex)
    if(re.findall(flag_regex, extra_str) == []):
        flag_list.append(extra_str)

    extra_regex = r'^[a-zA-Z0-9_{}]{37}$'
    extra_str = rstr.xeger(extra_regex)
    if(re.findall(flag_regex, extra_str) == []):
        flag_list.append(extra_str)

    extra_regex = r'^havceCTF{[A-Z]{2}_[a-ep-zA-LO-Z0-9]{10}_[a-z0-25-9]{7}_[a-zA-Z0-9]{5}}$'
    extra_str = rstr.xeger(extra_regex)
    if(re.findall(flag_regex, extra_str) == []):
        flag_list.append(extra_str)

    extra_regex = r'^[havceCTF]{8}{[A-Z]{2}_[a-ep-zA-LO-Z0-9]{10}_[a-z0-25-9]{7}_[a-zA-Z0-9]{5}}$'
    extra_str = rstr.xeger(extra_regex)
    if(re.findall(flag_regex, extra_str) == []):
        flag_list.append(extra_str)

    extra_regex = r'^havce[CTF]{3}{[a-zA-Z0-9]{5}_[A-Z]{2}_[a-z0-25-9]{7}_[a-ep-zA-LO-Z0-9]{10}}$'
    extra_str = rstr.xeger(extra_regex)
    if(re.findall(flag_regex, extra_str) == []):
        flag_list.append(extra_str)

    extra_regex = r'^[havce]{5}CTF{[a-zA-Z0-9]{5}_[A-Z]{2}_[a-z0-25-9]{7}_[a-ep-zA-LO-Z0-9]{10}}$'
    extra_str = rstr.xeger(extra_regex)
    if(re.findall(flag_regex, extra_str) == []):
        flag_list.append(extra_str)

    extra_regex = r'^havceCTF{[a-zA-Z0-9_]{28}$'
    extra_str = rstr.xeger(extra_regex)
    if(re.findall(flag_regex, extra_str) == []):
        flag_list.append(extra_str)

    extra_regex = r'^havceCTF[a-zA-Z0-9_]{28}}$'
    extra_str = rstr.xeger(extra_regex)
    if(re.findall(flag_regex, extra_str) == []):
        flag_list.append(extra_str)

    extra_regex = r'^havceCTF{[a-zA-Z0-9]{5}_[a-zA-Z0-9]{2}_[a-zA-Z0-9]{7}_[a-zA-Z0-9]{10}}$'
    extra_str = rstr.xeger(extra_regex)
    if(re.findall(flag_regex, extra_str) == []):
        flag_list.append(extra_str)

    extra_regex = r'^havceCTF{[a-zA-Z0-9]{5}_[A-Z]{2}_[a-z0-9]{7}_[a-zA-Z0-9]{10}}$'
    extra_str = rstr.xeger(extra_regex)
    if(re.findall(flag_regex, extra_str) == []):
        flag_list.append(extra_str)

# shuffle flag_list
random.shuffle(flag_list)

# write to file
with open("flag_list.txt", "w") as f:
    for flag in flag_list:
        print(flag, file=f)