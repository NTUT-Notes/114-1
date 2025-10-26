# Code for birthday paradox with simulation experiments
import random

def choice(data: list):
    return data[ random.randrange(0, len(data)) ]

# Return True if at least two people out of n share the same birthday.
def duplicate_birthdays(n):
    days = [ i+1 for i in range(365) ]

    seen = []
    for _ in range(n):
        selected = choice(days)
        if selected in seen:
            return True

        seen.append(selected) 

    return False

# Run experiments for n = 5, 10, ..., 100 with 10000 trials respectively and print frequencies(probabilities).
def birthday_paradox_experiment(trials=10000):
    print(f"{'n':>5} | {'Probability of Shared Birthday':>30}")
    print("-" * 40)
    
    for n in range(5, 101, 5):
        count_shared = sum(duplicate_birthdays(n) for _ in range(trials))
        probability = count_shared / trials
        print(f"{n:>5} | {probability:>30.4f}")

# Run the simulation
birthday_paradox_experiment()