import random

class Bogo:

    def __init__(self):
        self.count = 0

        print("""\n
 /$$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$  /$$$$$$$  /$$$$$$$$
| $$__  $$ /$$__  $$ /$$__  $$ /$$__  $$ /$$__  $$ /$$__  $$| $$__  $$|__  $$__/
| $$  \ $$| $$  \ $$| $$  \__/| $$  \ $$| $$  \__/| $$  \ $$| $$  \ $$   | $$   
| $$$$$$$ | $$  | $$| $$ /$$$$| $$  | $$|  $$$$$$ | $$  | $$| $$$$$$$/   | $$   
| $$__  $$| $$  | $$| $$|_  $$| $$  | $$ \____  $$| $$  | $$| $$__  $$   | $$   
| $$  \ $$| $$  | $$| $$  \ $$| $$  | $$ /$$  \ $$| $$  | $$| $$  \ $$   | $$   
| $$$$$$$/|  $$$$$$/|  $$$$$$/|  $$$$$$/|  $$$$$$/|  $$$$$$/| $$  | $$   | $$   
|_______/  \______/  \______/  \______/  \______/  \______/ |__/  |__/   |__/   \n
""")

    def sort(self, arr):
        self.count = 0

        is_sorted = False

        if not isinstance(arr, list) or arr is None or len(arr) == 0:
            raise RuntimeError("Invalid Array!")
        
        while not is_sorted:

            swapped = False
            
            random.shuffle(arr)
            self.count += 1

            for i in range(1, len(arr), 1):

                if arr[i][0] < arr[i - 1][0]:
                    swapped = True

            if not swapped:
                is_sorted = True

        return arr


