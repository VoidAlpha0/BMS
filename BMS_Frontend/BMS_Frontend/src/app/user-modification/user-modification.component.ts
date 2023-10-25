import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service'; // Import the UserService or the service you are using to manage users
import { Router } from '@angular/router';
@Component({
  selector: 'app-user-modification',
  templateUrl: './user-modification.component.html',
  styleUrls: ['./user-modification.component.css']
})
export class UserModificationComponent implements OnInit {
  users: any[]; // Define the users array to store user data

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    // Load the list of users when the component is initialized
    this.getAllUsers();
  }

  getAllUsers() {
    // Fetch the list of users from the service
    this.userService.getAllUsers().subscribe(
      (data) => {
        this.users = data;
      },
      (error) => {
        console.log('Error fetching users', error);
      }
    );
  }

  deleteUser(userId: number) {
    // Implement the logic to remove a user by their ID
   
    this.userService.deleteUser(userId).subscribe(
      () => {
        console.log('User deleted successfully');
        this.getAllUsers();
      },
      (error) => {
        console.log('Error deleting user', error);
      }
    );
  }


}
