class LegacyAPI {
    @Deprecated
    public void oldFeature()
    {
        System.out.println("This feature is deprecated. Use newFeature() instead.");
    }
    public void newFeature()
    {
        System.out.println("This is the new and recommended feature.");
    }
    public static void main(String[] args)
    {
        LegacyAPI api = new LegacyAPI();
        api.oldFeature();
        api.newFeature();
    }
}
