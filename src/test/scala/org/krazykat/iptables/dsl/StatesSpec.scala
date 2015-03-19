package org.krazykat.iptables.dsl

import org.specs2.mutable.Specification

class StatesSpec extends Specification{
  "Generate state for --state" should {
    "generate single state statement" in {
      val state = States(Established())
      state.string.replaceAll("\\s+"  , " ") must be equalTo(" --state ESTABLISHED ")
    }
    "generate two states statement" in {
      val state = States(Established(), Related())
      state.string.replaceAll("\\s+"  , " ") must be equalTo(" --state ESTABLISHED,RELATED ")
    }
    "generate two states statement and avoid repitative states" in {
      val state = States(Established(), Related(), Established())
      state.string.replaceAll("\\s+"  , " ") must be equalTo(" --state ESTABLISHED,RELATED ")
    }
    "generate three states statement" in {
      val state = States(Established(), Related(), New())
      state.string.replaceAll("\\s+"  , " ") must be equalTo(" --state ESTABLISHED,RELATED,NEW ")
    }
  }
  
  "Generate state for --ctstate" should {
    "generate single ctstate statement" in {
      val state = CTStates(Established())
      state.string.replaceAll("\\s+"  , " ") must be equalTo(" --ctstate ESTABLISHED ")
    }
    "generate two ctstates statement" in {
      val state = CTStates(Established(), Related())
      state.string.replaceAll("\\s+"  , " ") must be equalTo(" --ctstate ESTABLISHED,RELATED ")
    }
    "generate two ctstates statement and avoid repitative states" in {
      val state = CTStates(Established(), Related(), Established())
      state.string.replaceAll("\\s+"  , " ") must be equalTo(" --ctstate ESTABLISHED,RELATED ")
    }
    "generate three ctstates statement" in {
      val state = CTStates(Established(), Related(), New())
      state.string.replaceAll("\\s+"  , " ") must be equalTo(" --ctstate ESTABLISHED,RELATED,NEW ")
    }
  }
}