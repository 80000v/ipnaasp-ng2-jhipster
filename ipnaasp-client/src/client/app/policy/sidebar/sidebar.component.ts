import {Component, Input } from '@angular/core';
import { PolicyService } from '../../shared/index';

@Component({
  moduleId: module.id,
  selector: 'sidebar-full',
  templateUrl:'sidebar.component.html',
  styleUrls:['sidebar.component.css']
})
export class SideBarComponent {

  sidebarTypeArray:Array = ["myAllPolicy","myWaitPolicy","myEnterPolicy","myExitPolicy","subAllPolicy","subWaitPolicy","subEnterPolicy","subExitPolicy"];
  isOn:Array = [false,false,true,false,false,false,false,false];

  constructor(private _policyService: PolicyService) {}

  changeSideBar(type:string){
    this.isOn = [false,false,false,false,false,false,false,false];
    let i:number = 0;
    for(i=0; i<this.sidebarTypeArray.length; i++){
      if(this.sidebarTypeArray[i] === type){
        this.isOn[i] = true;
      }
    }
  }

  clickToMyAllPolicy(){
    this.changeSideBar(this.sidebarTypeArray[0]);
    this._policyService.policyType = "我的策略";
    this._policyService.curType = "";
    this._policyService._curTypeFlag = false;
    this._policyService.policyStatuse = "All";
    this._policyService.policyIDValue = "";
    this._policyService.policyTypeValue = "";
    this._policyService.policyDirectionValue = "";
    this._policyService.policyCycleValue = "";

    this._policyService.queryMyPolicies(this._policyService.policyStatuse,null,null,null,null);
  }

  clickToMyWaitPolicy(){
    this.changeSideBar(this.sidebarTypeArray[1]);
    this._policyService.policyType = "我的策略";
    this._policyService.curType = "待入场";
    this._policyService._curTypeFlag = true;
    this._policyService.policyStatuse = "待入场";

    this._policyService.policyIDValue = "";
    this._policyService.policyTypeValue = "";
    this._policyService.policyDirectionValue = "";
    this._policyService.policyCycleValue = "";
    this._policyService.queryMyPolicies(this._policyService.policyStatuse,null,null,null,null);
  }

  clickToMyEnterPolicy(){
    this.changeSideBar(this.sidebarTypeArray[2]);
    this._policyService.policyType = "我的策略";
    this._policyService.curType = "已入场";
    this._policyService._curTypeFlag = true;
    this._policyService.policyStatuse = "已入场";

    this._policyService.policyIDValue = "";
    this._policyService.policyTypeValue = "";
    this._policyService.policyDirectionValue = "";
    this._policyService.policyCycleValue = "";
    this._policyService.queryMyPolicies(this._policyService.policyStatuse,null,null,null,null);
  }

  clickToMyExitPolicy(){
    this.changeSideBar(this.sidebarTypeArray[3]);
    this._policyService.policyType = "我的策略";
    this._policyService.curType = "已出场";
    this._policyService._curTypeFlag = true;
    this._policyService.policyStatuse = "已退场";
    this._policyService.policyIDValue = "";
    this._policyService.policyTypeValue = "";
    this._policyService.policyDirectionValue = "";
    this._policyService.policyCycleValue = "";
    this._policyService.queryMyPolicies(this._policyService.policyStatuse,null,null,null,null);
  }

  clickToSubAllPolicy(){
    this.changeSideBar(this.sidebarTypeArray[4]);
    this._policyService.policyType = "订阅的策略";
    this._policyService.curType = "";
    this._policyService._curTypeFlag = false;
  }

  clickToSubWaitPolicy(){
    this.changeSideBar(this.sidebarTypeArray[5]);
    this._policyService.policyType = "订阅的策略";
    this._policyService.curType = "待入场";
    this._policyService._curTypeFlag = true;
  }

  clickToSubEnterPolicy(){
    this.changeSideBar(this.sidebarTypeArray[6]);
    this._policyService.policyType = "订阅的策略";
    this._policyService.curType = "已入场";
    this._policyService._curTypeFlag = true;
  }

  clickToSubExitPolicy(){
    this.changeSideBar(this.sidebarTypeArray[7]);
    this._policyService.policyType = "订阅的策略";
    this._policyService.curType = "已退场";
    this._policyService._curTypeFlag = true;
  }
}
