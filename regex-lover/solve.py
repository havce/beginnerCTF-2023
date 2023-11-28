import re

flag_regex = r'^havceCTF{[a-zA-Z0-9]{5}_[A-Z]{2}_[a-z0-25-9]{7}_[a-ep-zA-LO-Z0-9]{10}}$'

with open("flag_list.txt", "r") as f:
	for line in f:
		res = re.findall(flag_regex, line.strip())
		if(res):
			print(res)

# flag: havceCTF{QnJiY_LP_o2j8ypc_v60qcrrwAU}