def find_majority_element(nums):
    candidate = None
    count = 0

    for num in nums:
        if count == 0:
            candidate = num
            count = 1
        elif num == candidate:
            count += 1
        else:
            count -= 1

    return candidate

def is_majority_element(nums, candidate):
    return nums.count(candidate) > len(nums) // 2

# Example usage:
input_list = [4, 4, 7, 3, 4, 6, 4, 8, 8, 1, 2, 2, 4, 4, 5, 1, 4, 4, 4, 9, 4, 6, 1, 7, 4, 4, 4, 4, 9, 4, 4, 3, 1, 4, 4, 5, 8, 4, 6, 9, 7, 4, 4, 4, 4, 4, 7, 2, 6, 4, 5, 4, 3, 4, 9, 8, 2, 4, 4, 9, 4, 4, 4, 6, 1, 4, 4, 7, 4, 4, 6, 3, 3, 4, 5, 8, 4, 9, 4, 4, 4, 5, 4, 2, 2, 8, 1, 7, 4, 4, 5, 4, 4, 4, 1, 4, 7, 4, 3]
majority_candidate = find_majority_element(input_list)
print(f"The majority candidate is {majority_candidate}")
print(f"{majority_candidate} appears {input_list.count(majority_candidate)} times in the list.")
print(f"The length of the list is {len(input_list)}")
print(f"The length of the list divided by 2 is {len(input_list) // 2}")
if is_majority_element(input_list, majority_candidate):
    print(f"The majority element is {majority_candidate}")
else:
    print("There is no majority element in the list.")