class Bubble:

    def __init__(self):
        self.count = 0

        print("""\n
 /$$$$$$$  /$$   /$$ /$$$$$$$  /$$$$$$$  /$$       /$$$$$$$$  /$$$$$$   /$$$$$$  /$$$$$$$  /$$$$$$$$
| $$__  $$| $$  | $$| $$__  $$| $$__  $$| $$      | $$_____/ /$$__  $$ /$$__  $$| $$__  $$|__  $$__/
| $$  \ $$| $$  | $$| $$  \ $$| $$  \ $$| $$      | $$      | $$  \__/| $$  \ $$| $$  \ $$   | $$   
| $$$$$$$ | $$  | $$| $$$$$$$ | $$$$$$$ | $$      | $$$$$   |  $$$$$$ | $$  | $$| $$$$$$$/   | $$   
| $$__  $$| $$  | $$| $$__  $$| $$__  $$| $$      | $$__/    \____  $$| $$  | $$| $$__  $$   | $$   
| $$  \ $$| $$  | $$| $$  \ $$| $$  \ $$| $$      | $$       /$$  \ $$| $$  | $$| $$  \ $$   | $$   
| $$$$$$$/|  $$$$$$/| $$$$$$$/| $$$$$$$/| $$$$$$$$| $$$$$$$$|  $$$$$$/|  $$$$$$/| $$  | $$   | $$   
|_______/  \______/ |_______/ |_______/ |________/|________/ \______/  \______/ |__/  |__/   |__/   \n
""")


    def sort(self, arr):
        self.count = 0

        is_sorted  = False

        if not isinstance(arr, list) or arr is None or len(arr) == 0:
            raise RuntimeError("Invalid Array!")

        while not is_sorted:
            swapped = False

            for i in range(1, len(arr)):

                if arr[i - 1][0] > arr[i][0]:
                    arr[i - 1], arr[i] = arr[i], arr[i - 1]

                    self.count += 1
                    swapped = True

            if not swapped:
                is_sorted  = True

        return arr