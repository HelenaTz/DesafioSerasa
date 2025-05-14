import sys
import json

def calculate_score(data):
    idade = data["idade"]
    num_bens = len(data["bens"])
    renda = data["rendaMensal"]

    score = (idade * 0.2) + (num_bens * 100) + (renda * 0.5)
    return min(score, 1000)

if __name__ == "__main__":
    input_json = sys.argv[1]
    data = json.loads(input_json)
    print(calculate_score(data))