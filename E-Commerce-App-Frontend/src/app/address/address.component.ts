import { Component, Input, OnInit } from '@angular/core';
import { AddressService } from '../_services/address.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import Address from '../_models/address';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {

  @Input() id!: any;
  @Input() addressName = "";
  @Input() country = "";
  @Input() city = "";
  @Input() state = "";
  @Input() zipcode!: number;
  @Input() phone!: number;

  address: Address = new Address();
  private roles: string[] = [];
  isLoggedIn = false;
  isCustomer = false;
  isSubmitted = false;
  hasAddress = false;

  constructor(private tokenStorageService: TokenStorageService, private router: Router,
    private addressService: AddressService, private toastrService: ToastrService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.isCustomer = this.roles.includes('ROLE_CUSTOMER');

      this.addressService
        .getAddressForUser(this.tokenStorageService.getUser().id).subscribe(data => {
          console.log(data);
          if (data !== null) {
            this.address = data;
          }
          else {
            this.address = new Address();
            this.hasAddress = false;
          }
        },
          error => {
            console.error(error);
          });
    }
  }

  showSuccess() {
    this.toastrService.success('Address sucessfully updated!');
  }

  showFailure() {
    this.toastrService.error('Could not update address!');
  }

  goToHomePage() {
    this.router.navigate(['/home']);
  }

  save() {
    if (this.hasAddress) {
      this.addressService
        .updateAddress(this.address.id, this.address).subscribe(data => {
          console.log(data);
          this.showSuccess();
          this.goToHomePage();
        },
          error => {
            console.error(error);
            this.showFailure();
            location.reload();
          });
    }
    else {
      this.addressService
        .addAddressForUser(this.address, this.tokenStorageService.getUser().id).subscribe(data => {
          console.log(data);
          this.address = new Address();
          this.showSuccess();
          this.goToHomePage();
        },
          error => {
            console.error(error);
            this.showFailure();
            location.reload();
          });
    }
  }

  onSubmit() {
    this.isSubmitted = true;
    this.save();
  }

}
