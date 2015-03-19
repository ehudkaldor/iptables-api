package org.krazykat.iptables.dsl

import org.specs2.mutable.Specification
import org.krazykat.iptables.dsl.Implicits._

class CommandSpec extends Specification {
  
  "Generate iptables commands" should {
    "generate delete command with no table and rulenum 5" in {
      val command = Delete(Input(), RuleNum(5))
      "iptables --table filter --delete INPUT 5" must beMatching(command) 

//      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table filter --delete INPUT 5 ")
    }
    "generate delete command with table nat and rulenum 10" in {
      val command = Delete(Nat(), Output(), RuleNum(10))
      "iptables --table nat --delete OUTPUT 10" must beMatching(command) 
//      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table nat --delete OUTPUT 10 ")
    }
  }
  
  "Generate flush commands" should {
    "generate flush command with table nat and chain INPUT" in {
      val command = Flush(Nat(), Some(Input()))
      "iptables --table nat --flush INPUT" must beMatching(command) 
//      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table nat --flush INPUT ")
    }
    "generate flush command with no table and chain FORWARD" in {
      val command = Flush(Some(Forward()))
      "iptables --table filter --flush FORWARD" must beMatching(command) 
//      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table filter --flush FORWARD ")
    }
    "generate flush command with no table and no chain" in {
      val command = Flush(None)
      "iptables --table filter --flush" must beMatching(command) 
//      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table filter --flush ")
    }
  }
  
  "Generate zero commands" should {
    "generate zero command with no table and no chain" in {
      val command = Zero(None, None)
      "iptables --table filter --zero" must beMatching(command) 
//      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table filter --zero ")
    }
    "generate zero command with table raw and no chain" in {
      val command = Zero(Raw(), None)
      "iptables --table raw --zero" must beMatching(command) 
//      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table raw --zero ")
    }
    "generate zero command with no table and chain FORWARD" in {
      val command = Zero(Some(Forward()))
      "iptables --table filter --zero FORWARD" must beMatching(command) 
//      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table filter --zero FORWARD ")
    }
    "generate zero command with table nat and chain INPUT and no rule number" in {
      val command = Zero(Nat(), Some(Input()))
      "iptables --table nat --zero INPUT" must beMatching(command) 
//      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table nat --zero INPUT ")
    }
    "generate zero command with table mangle and chain INPUT and rule num 3" in {
      val command = Zero(Mangle(), Some(Input()), Some(RuleNum(3)))
      "iptables --table mangle --zero INPUT 3" must beMatching(command) 
//      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table mangle --zero INPUT 3 ")
    }
    "generate zero command with no table and chain OUTPUT and rule num 15" in {
      val command = Zero(Some(Output()), Some(RuleNum(15)))
      "iptables --table filter --zero OUTPUT 15" must beMatching(command) 
//      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table filter --zero OUTPUT 15 ")
    }
  }
  
  "Generate policy statement" should {
    "generate --policy statement for ACCEPT with default table and INPUT chain" in {
      val policy = SetPolicy(Input(), Accept())
      s"iptables --table filter --policy INPUT ACCEPT" must beMatching(policy)       
    }
    "generate --policy statement for DROP with table nat and OUTPUT chain" in {
      val policy = SetPolicy(Nat(), Output(), Drop())
      s"iptables --table nat --policy OUTPUT DROP" must beMatching(policy)       
    }
  }
  
}