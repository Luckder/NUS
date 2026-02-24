from Bubble import Bubble
from Bogo import Bogo
from Cosmic import Cosmic
from Merge import Merge
from Quick import Quick
import random
import time
import threading

def print_status(stop_event, start_time):
    while not stop_event.is_set():
        time.sleep(60)

        if not stop_event.is_set():
            print(f"{(time.time() - start_time):.6f} seconds elapsed. Still running...")

def main():

    algorithms = {
        "bubble": Bubble,
        "bogo": Bogo,
        "cosmic": Cosmic,
        "merge": Merge,
        "quick": Quick
    }

    lang = input("\nWhat would you like to do? Sort/Competition\n").strip().lower()

    match lang:
        case "sort":

            user_input = input("\nWhich sorting algorithm? ").strip().lower()

            if user_input not in algorithms:
                print(f"Error: '{user_input}' sorting algorithm not found!")
                print(f"Available algorithms: {', '.join(algorithms.keys())}")
                return

            SortingClass = algorithms[user_input]
            sorter = SortingClass()

            repeat = True

            while repeat:

                (limit, num) = getRandomArray()

                work(sorter, limit, num.copy())

                user_input3 = input("Again? [Y/N]: ").strip().lower()

                if user_input3 == 'y':
                    repeat = True
                else:
                    repeat = False

            

        case "competition":
            user_input1 = input("Which sorting algorithm1? ").strip().lower()

            if user_input1 not in algorithms:
                print(f"Error: '{user_input1}' sorting algorithm not found!")
                print(f"Available algorithms: {', '.join(algorithms.keys())}")
                return

            SortingClass1 = algorithms[user_input1]
            sorter1 = SortingClass1()

            user_input = input("Which sorting algorithm2? ").strip().lower()

            if user_input not in algorithms:
                print(f"Error: '{user_input}' sorting algorithm not found!")
                print(f"Available algorithms: {', '.join(algorithms.keys())}")
                return

            SortingClass2 = algorithms[user_input]
            sorter2 = SortingClass2()


            repeat = True

            while repeat:

                (limit, num) = getRandomArray()

                work(sorter1, limit, num.copy())
                work(sorter2, limit, num.copy())

                user_input3 = input("Again? [Y/N]: ").strip().lower()

                if user_input3 == 'y':
                    repeat = True
                else:
                    repeat = False

def getRandomArray():
    limit = random.randint(10, 100001)

    return (limit, [(random.randint(10, 100000), i) for i in range(limit)])

def work(sorter, limit, num):

    #print("\n")
    #print(num)
    #print("\n")

    print(f"\nRunning {sorter.__class__.__name__}Sort...")

    print(f"Size is {limit}")

    stop_event = threading.Event()
    start_time2 = time.time()
    status_thread = threading.Thread(target=print_status, args=(stop_event, start_time2))
    status_thread.daemon = True
    status_thread.start()

    start_time = time.time()

    sorted_arr = sorter.sort(num)

    end_time = time.time()

    stop_event.set()
    status_thread.join(timeout=1)

    elapsed_time = end_time - start_time

    #print(num)
    #print("\n")
    print(f"{sorter.count} swaps")
    is_stable(sorted_arr)
    print(f"Took {elapsed_time:.6f} seconds\n")


def is_stable(arr):

    stable = True

    for n in range(1, len(arr)):

        if arr[n - 1][1] > arr[n][1] and arr[n - 1][0] == arr[n][0]:
            stable = False
            break
        
    if stable:
        print("The sorting was STABLE.")
    else:
        print("The sorting was UNSTABLE.")


if __name__ == "__main__":
    main()