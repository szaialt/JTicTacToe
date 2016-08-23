public enum GamerIdentifier {
  //Identifiers of the two gamers
  DEFENDER, ATTACKER;
 
   //The adversary player
  public GamerIdentifier getAdversary() {
   if (this.equals(GamerIdentifier.DEFENDER))
     return GamerIdentifier.ATTACKER;
   else
     return GamerIdentifier.DEFENDER;
  }

}
