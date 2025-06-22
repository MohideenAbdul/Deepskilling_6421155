public class BuilderPatternExample {
    public static void main(String[] args) {
        Computer basicComputer = new Computer.Builder("Intel i5", "8GB")
                .build();

        Computer gamingComputer = new Computer.Builder("Intel i9", "32GB")
                .storage("1TB SSD")
                .graphicsCard("NVIDIA RTX 4090")
                .powerSupply("850W")
                .build();

        Computer officeComputer = new Computer.Builder("AMD Ryzen 5", "16GB")
                .storage("512GB SSD")
                .powerSupply("500W")
                .build();

        System.out.println(basicComputer);
        System.out.println(gamingComputer);
        System.out.println(officeComputer);
    }
}

class Computer {
    private final String cpu;
    private final String ram;
    private final String storage;
    private final String graphicsCard;
    private final String powerSupply;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.powerSupply = builder.powerSupply;
    }

    public static class Builder {
        private final String cpu;
        private final String ram;
        private String storage;
        private String graphicsCard;
        private String powerSupply;

        public Builder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder graphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public Builder powerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    @Override
    public String toString() {
        return "Computer [cpu=" + cpu + ", ram=" + ram + ", storage=" + storage +
                ", graphicsCard=" + graphicsCard + ", powerSupply=" + powerSupply + "]";
    }
}
