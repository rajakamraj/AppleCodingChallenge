import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {User} from "../../model/user";
import {Tree} from "../../model/tree";
import {Router} from "@angular/router";
import {MatDialog} from '@angular/material/dialog';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: User;
  currentTree: Tree = new Tree();
  errorMessage: String;



  constructor(private authService: AuthService, private router: Router,public dialog: MatDialog) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {

  }

  onCommentChange(ev){
    this.currentTree.data = ev.target.value;
    console.log(this.currentTree.data);
  }
  calculate(){
    this.authService.calculate(this.currentTree.data).subscribe(data => {
      this.currentTree.answer=data;
    },err =>{
      this.errorMessage ="The input data is not valid";
      const dialogErrorRef = this.dialog.open(DialogContentErrorDialog);

      dialogErrorRef.afterClosed().subscribe(result => {
        console.log(`Dialog result: ${result}`);
      });
    });
  }

  openDialog() {
    const dialogRef = this.dialog.open(DialogContentExampleDialog);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }


  logOut(){
    this.authService.logOut()
    .subscribe(data =>{
      this.currentUser = null;
      this.router.navigate(['/login']);
    },err => {

    });
  }

}
@Component({
  selector: 'dialog-content-example-dialog',
  templateUrl: './dialog-content-example-dialog.html',
})
export class DialogContentExampleDialog {}

@Component({
  selector: 'dialog-content-error-input',
  templateUrl: './dialog-content-error-input.html',
})
export class DialogContentErrorDialog {}
