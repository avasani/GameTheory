/**
* All rights reserved, wOw.
*/

import java.util.Random;

/**
 * @author bey0ndz
 *
 */
public class SolarBatteryAlgorithm {
	public void solarBatteryUtility() {
		// we have two time slots, t1 = 1
		// t2 = 2
		int[] timeSlots = new int[2];
		timeSlots[0] = 1;
		timeSlots[1] = 2;
		
		Random rand = new Random();
			
		// allocation to user
		int[] allocation = new int[2];
		allocation[0] = rand.nextInt(100) + 1;
		allocation[1] = rand.nextInt(100) + 1;
		
		// solar energy generation
		int[] solarEnergy = new int[2];
		solarEnergy[0] = rand.nextInt(100) + 1;
		solarEnergy[1] = rand.nextInt(100) + 1;
		
		// utility
		int energyDiff = 0;
		int batteryCharge = 10;
		int energyFromBattery = 0;
		int moneyLost = 0;
xc		
		// for each of the time slot, calculate the utility
		// utility is expressed as solar - alloc
		for (int i=0; i<timeSlots.length; i++) {
			for (int j=0; j<allocation.length; j++) {
				energyDiff = solarEnergy[j]- allocation[j];
				System.out.println("Energy difference obtained: "+energyDiff);
				
				// see if the energy diff obtained is greater than
				// or less than zero
				if (energyDiff > 0) {
					// solar energy is more
					// if solar energy is more
					// charge the battery
					// we SHOULD optimize this to add the charging
					// discharging rate
					System.out.println("Energy difference is > 0. Charging battery upto: "+energyDiff);
					batteryCharge = energyDiff;
					
				} else if (energyDiff < 0){
					System.out.println("Energy difference is < 0. Checking battery charge.");
					// solar energy is being generated less
					// check the battery
					if (batteryCharge > 0) {
						// energy can be obtained from battery
						System.out.println("Battery charge is > 0. Discharging from battery.");
						energyFromBattery = batteryCharge - Math.abs(energyDiff);
						
						// check how much energy is needed
						// from battery
						if (energyFromBattery > 0) {
							System.out.println("We have enough battery to support us.");
							// awesome, battery saved us
							batteryCharge = batteryCharge - energyFromBattery;
						} else if (energyFromBattery < 0) {
							// we need to get some energy
							// from the provider
							// for which we need to pay depending on the
							// time slot
							System.out.println("Our battery will not support us. We need to get energy from the utility provider.");
							if (i==1) {
								// first time slot
								moneyLost = Math.abs(energyFromBattery) * 2;
								System.out.println("Since it is the first time slot, we pay $2 for every kW. We end up paying: "+moneyLost);
							} else {
								// second time slot
								moneyLost = Math.abs(energyFromBattery) * 4;
								System.out.println("Since it is the second time slot, we pay $4 for every kW. We end up paying: "+moneyLost);
							} 
						}
					} else {
						// we had exactly the same
						// then see if it is time slot 2 and
						// charge the battery
						if (i==2) {
							// charge the battery
							batteryCharge = batteryCharge + 17;
							moneyLost = 17 * 2;
							System.out.println("Charging battery since we have used up energy from battery. Money lost: "+moneyLost);
						}
					}
				} else {
					// solar and alloc cancel out
					// we are happy
					// but if it is off peak hours (t2)
					// we could just charge the battery
					if (i==2) {
						// charge the battery
						batteryCharge = batteryCharge + 17;
						moneyLost = 17*2;
						System.out.println("We have exactly the same solar power as allocation. So, charging battery for a rainy day. Money lost: "+moneyLost);
					}
				}
			}
		}
	}
} 
