import os
import shutil

# Ensure that the user is at the cosc600 directory
if not os.path.split(os.getcwd())[1] == "cosc600":
    print("Error: Please run this script from the cosc600 directory.")
    exit(1)

# Define the project directory and src directory
# Get the project name from the user
project_name = input("Enter the project name: ")
# If the project name has spaces, replace them with dashes
project_name = project_name.replace(" ", "-")
project_dir = os.path.join(os.getcwd(), project_name)
src_dir = os.path.join(project_dir, "src")

# Create the project directory
if not os.path.exists(project_dir):
    os.makedirs(project_dir)

# Create the src directory
if not os.path.exists(src_dir):
    os.makedirs(src_dir)

# Create the Solution.java file within the src directory with a main function
solution_code = """
public class Solution {
    public static void main(String[] args) {
        // Your code here
        System.out.println("Hello, World!");
    }
}
"""

with open(os.path.join(src_dir, "Solution.java"), "w") as file:
    file.write(solution_code)

# Copy the makefile from the script directory to the project directory
script_dir = os.path.dirname(os.path.abspath(__file__))
makefile_src = os.path.join(script_dir, "Makefile")
makefile_dest = os.path.join(project_dir, "Makefile")

if os.path.exists(makefile_src):
    shutil.copy(makefile_src, makefile_dest)
else:
    print("Warning: makefile not found in the script directory.")

print(
    f"Java project '{project_dir}' has been created with a Solution.java file and makefile.")
