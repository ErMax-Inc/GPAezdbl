# ErMax GPA Ezdbl

ErMax GPA Ezdbl is a Java application designed to help students manage their course grades and calculate both unweighted and weighted GPAs easily. With this application, users can add, delete, update, and reload course information from a connected database.

## Features

- **Add Courses**: Users can add new courses along with their semester 1 grade, semester 2 grade, and weight.
- **Delete Courses**: Courses can be deleted from the database, which removes them from the displayed table.
- **Update Courses**: Users can update existing course information, such as grades or weight.
- **Reload From DB**: Refreshes the table view by fetching the latest data from the connected database.
- **Calculate GPA**: Calculates both unweighted and weighted GPAs based on the entered course grades and weights.
- **Hide/Unhide Grades**: Allows users to toggle the visibility of their grades for **Privacy**, with a JOptionPane to **Confirm Your Choice**.

## Getting Started

To run the application, follow these steps:

1. Clone this repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Make sure you have the necessary dependencies installed, such as JDBC driver for MS Access database.
4. Compile and run the `ezdblWindow.java` file.
5. You should now see the application window with the table view of courses.

## Requirements
- MS Access JDBC Driver (if using an MS Access database)

## Usage

1. **Adding a Course**:
   - Click on the "Add" button.
   - Enter the course name, semester 1 grade, semester 2 grade, and weight.
   - Click "Add" to save the course to the database and update the table view.

2. **Deleting a Course**:
   - Select the course from the table.
   - Click on the "Delete" button to remove the course from the database and table view.

3. **Updating a Course**:
   - Select the course from the table.
   - Modify the course details in the input fields.
   - Click on the "Update" button to save the changes to the database and update the table view.

4. **Reloading From DB**:
   - Click on the "Reload From DB" button to fetch the latest course data from the connected database.

5. **Calculating GPA**:
   - Click on the "Calculate" button to calculate both unweighted and weighted GPAs based on the entered course grades and weights.

6. **Hide/Unhide Grades**:
   - Click on the "Hide/Unhide" button to toggle the visibility of your grades.

## Known Exploits

1. **SQL Injection** (Major unless database is only used for this program by trusted users)
   - Keep in mind that this is a simple app designed to quickly but accurately calculate your GPA; the table input directly correlates with what you are putting in (no preventative string data parsing)

Enjoy my calculator,

- mw

## License

This project is licensed under the [License Name] License - see the [LICENSE.md](LICENSE.md) file for details.
