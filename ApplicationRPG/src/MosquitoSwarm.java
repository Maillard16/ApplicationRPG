
public class MosquitoSwarm extends Threat {
    private int mosquitoNbr;
    
    public MosquitoSwarm(int mosquitoNbr) {
        this.mosquitoNbr = mosquitoNbr;
    }
    
    public int getMosquitoNbr() {
        return mosquitoNbr;
    }
    
    public void setMosquitoNbr(int mosquitoNbr) {
        this.mosquitoNbr = mosquitoNbr;
        if(mosquitoNbr == 0) {
            setIsThreatning(false);
        }
    }

	@Override
	public void dealDamage() {
		getThreathenedPlayer().loseLife(mosquitoNbr);
	}
}
