class Merge:

    def __init__(self):
        self.count = 0

        print("""\n
 /$$      /$$ /$$$$$$$$ /$$$$$$$   /$$$$$$  /$$$$$$$$  /$$$$$$   /$$$$$$  /$$$$$$$  /$$$$$$$$
| $$$    /$$$| $$_____/| $$__  $$ /$$__  $$| $$_____/ /$$__  $$ /$$__  $$| $$__  $$|__  $$__/
| $$$$  /$$$$| $$      | $$  \ $$| $$  \__/| $$      | $$  \__/| $$  \ $$| $$  \ $$   | $$   
| $$ $$/$$ $$| $$$$$   | $$$$$$$/| $$ /$$$$| $$$$$   |  $$$$$$ | $$  | $$| $$$$$$$/   | $$   
| $$  $$$| $$| $$__/   | $$__  $$| $$|_  $$| $$__/    \____  $$| $$  | $$| $$__  $$   | $$   
| $$\  $ | $$| $$      | $$  \ $$| $$  \ $$| $$       /$$  \ $$| $$  | $$| $$  \ $$   | $$   
| $$ \/  | $$| $$$$$$$$| $$  | $$|  $$$$$$/| $$$$$$$$|  $$$$$$/|  $$$$$$/| $$  | $$   | $$   
|__/     |__/|________/|__/  |__/ \______/ |________/ \______/  \______/ |__/  |__/   |__/   \n
""")
        
    def sort(self, arr):
        self.count = 0

        is_sorted  = False

        if not isinstance(arr, list) or arr is None or len(arr) == 0:
            raise RuntimeError("Invalid Array!")


        result = self.mergeSort(arr)


        return result
    
    def mergeSort(self, arr):
        if len(arr) <= 1:
            return arr

        mid = len(arr) // 2
        leftHalf = arr[:mid]
        rightHalf = arr[mid:]

        sortedLeft = self.mergeSort(leftHalf)
        sortedRight = self.mergeSort(rightHalf)

        return self.merge(sortedLeft, sortedRight)

    def merge(self, left, right):
        result = []
        i = j = 0

        while i < len(left) and j < len(right):
            if left[i][0] <= right[j][0]:
                result.append(left[i])
                i += 1
            else:
                result.append(right[j])
                j += 1
                self.count += len(left) - i

        result.extend(left[i:])
        result.extend(right[j:])

        return result