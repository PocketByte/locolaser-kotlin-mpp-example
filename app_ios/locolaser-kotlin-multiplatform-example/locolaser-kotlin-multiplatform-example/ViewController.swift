//
//  VievController.swift
//  locolaser-kotlin-multiplatform-example
//
//  Created by Dash on 28.05.2018.
//  Copyright © 2018 PcketByte.ru. All rights reserved.
//

import UIKit
import ios_app_framework

class ViewController: UIViewController, MainScreenContractView {
    
    @IBOutlet weak var labelMessage1: UILabel!
    @IBOutlet weak var labelMessage2: UILabel!
    @IBOutlet weak var labelMessage3: UILabel!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        MainScreenPresenter(view: self).start()
    }
    
    func showMessage1(message: String) {
        labelMessage1.text = message
    }
    
    func showMessage2(message: String) {
        labelMessage2.text = message
    }
    
    func showMessage3(message: String) {
        labelMessage3.text = message
    }
}
