package main

import (
	"encoding/json"
	"fmt"
	"math/rand"
	"time"
	"traffic/generator/kafka" 

)




var ruas = []string{
	"Marquês de Olinda",
	"Rua da Aurora",
	"Rua do Sol",
	"Rua do Imperador",
	"Rua do Príncipe",
	"Rua do Riachuelo",
	"Rua do Sol",
	"Rua Marechal Deodoro",
	"Rua Nova",
	"Rua Princesa Isabel",
	"Rua Vila nova",
	"Rua da Concórdia",
	"Rua 13 de Maio",
	"Rua do Progresso",
	"Rua do Sossego",
	"Rua Ibicui",
	"Rua do Hospício",
	"Rua do Lima",
	"Rua Ignácio Gonchorowski",
	"Rua Antônio Alves de Araújo",
	"BR-280",
	"Rua 15 de Novembro",
	"Rua 7 de Setembro",
	"Rua 25 de Julho",
	"Rua 25 de Dezembro",
	"Rua 1º de Maio",
	"Rua 1º de Janeiro",
	"BR-101",
	"BR-116",
	"BR-282",
	"BR-470",
	"BR-153",
	"SC-401",
	"SC-402",
	"SC-403",
	"SC-404",
	"RS-020",
	"RS-030",
	"RS-040",
	"RS-041",
	"RS-042",
	"Rua Praça Getúlio Vargas",
	"Rua Praça da Bandeira",
	"Rua Praça da República",
	"Rua Praça do Ferreira",
	"Rua Praça do Diário",
	"Rua Praça do Arsenal",
	"Rua Praça do Conde",
	"Rua Praça do Carmo",
	"Rua Jośe Carlos Hantschel",
	"Rua Henrique Liebl",

}
var bairros = []string{
	"Centro",
	"Boa Vista",
	"Glória",
	"Vila Nova",
	"Vila Lenzi",
	"Jardim Hantschel",
	"Cruzeiro",
	"Vila Lalau",
	"Vila Baependi",
	"Vila Rau",
	"Vila Nova",
	"Vila Lenzi",
	"Vista Alegre",
	"Industrial Norte",
	"Industrial Sul",
	"Três Rios do Norte",
	"Rio da Luz",
	"Rio Cerro I",
	"Rio Cerro II",
	"Rio Cerro III",

}


var direcoes = []string{
	"North",
	"South",
	"East",
	"West",
}

var streetDirections = []string{
	"North",
	"South",
	"East",
	"West",
}



var marcas = []string{
	"Alfa Romeo",
	"Audi",
	"BMW",
	"Chery",
	"Chevrolet",
	"Chrysler",
	"Citroën",
	"Dodge",
	"Ferrari",
	"Fiat",
	"Ford",
	"Geely",
	"Honda",
	"Hyundai",
	"JAC Motors",
	"Jaguar",
	"Jeep",
}



var carTypes = map[string][]string{
	"Alfa Romeo":   {"Sedan", "Sports Car", "Coupe"},
	"Audi":         {"Sedan", "SUV", "Sports Car", "Station Wagon"},
	"BMW":          {"Sedan", "SUV", "Sports Car", "Coupe"},
	"Chery":        {"Sedan", "Hatch"},
	"Chevrolet":    {"Sedan", "Pickup", "SUV"},
	"Chrysler":     {"Sedan", "SUV", "Coupe"},
	"Citroën":      {"Sedan", "Hatch", "Station Wagon"},
	"Dodge":        {"Sedan", "SUV", "Coupe"},
	"Ferrari":      {"Sports Car", "Coupe"},
	"Fiat":         {"Sedan", "Hatch", "Station Wagon"},
	"Ford":         {"Sedan", "Pickup", "SUV", "Coupe"},
	"Geely":        {"Sedan", "Hatch"},
	"Honda":        {"Sedan", "SUV", "Coupe"},
	"Hyundai":      {"Sedan", "SUV", "Coupe"},
	"JAC Motors":   {"Sedan", "Hatch"},
	"Jaguar":       {"Sedan", "SUV", "Coupe"},
	"Jeep":         {"SUV"},
}



var pollution = map[string]float64{
	"Alfa Romeo":  0.25,
	"Audi":        0.3,
	"BMW":         0.35,
	"Chery":       0.2,
	"Chevrolet":   0.4,
	"Chrysler":    0.45,
	"Citroën":     0.25,
	"Dodge":       0.5,
	"Ferrari":     0.6,
	"Fiat":        0.25,
	"Ford":        0.4,
	"Geely":       0.2,
	"Honda":       0.3,
	"Hyundai":     0.3,
	"JAC Motors":  0.2,
	"Jaguar":      0.5,
	"Jeep":        0.5,
}


//array de nome
var nomes = []string{
	"João",								
	"José",
	"Antônio",
	"Francisco",
	"Carlos",
	"Paulo",
	"Pedro",
	"Lucas",
	"Luiz",
	"Marcos",
	"Luiz",
	"Maria",
	"Ana",
	"Francisca",
	"Antônia",
	"Adriana",
	"Juliana",
	"Marcia",
	"Fernanda",
	"Patricia",
	"Aline",
	"Camila",
	"Carla",
	"Julia",
	"Vitoria",
	"Viviane",
	"Vivian",
	"Viviana",
	"Jorge",
	"André",
	"Ricardo",
	"Rodrigo",
	"Rafael",
	"Guilherme",
	"Gabriel",
	"Marcelo",
	"Luciano",
	"Lucas",
	"Leonardo",
}


var sobrenomes = []string{
	"Silva",
	"Santos",
	"Oliveira",
	"Souza",
	"Lima",
	"Pereira",
	"Almeida",
	"Alves",
	"Araújo",
	"Barbosa",
	"Barros",
	"Batista",
	"Bezerra",
	"Borges",
	"Cardoso",
	"Carvalho",
	"Castro",
	"Cavalcanti",
	"Correia",
	"Cunha",
	"Dias",
	"Duarte",
	"Farias",
	"Fernandes",
	"Ferreira",
	"Figueiredo",
	"Gomes",
	"Gonçalves",
	"Jesus",
	"Pscheidet",
	"Munt",
	"Reckziegel",
}


var acidentes = []string{
    "Front Collision",
    "Rear Collision",
    "Side Collision",
    "Rollover",
    "Side Swipe",
    "Hit and Run",
    "Vehicle/Pedestrian",
    "Vehicle/Animal",
    "Vehicle/Barrier",
    "Vehicle/Building",
    "Vehicle/Ditch",
    "Vehicle/Fence",
    "Vehicle/Guardrail",
    "Vehicle/Pole",
    "Aquaplaning",
    "Collision with Parked Objects",
    "Side Collision with Parked Objects",
    "Multi-Vehicle Collision",
    "Multiple Rolls",
    "Brake Failure",
    "Skidding",
    "Vehicle Fire",
    "Run-off Road",
}



var (
	// Inicialize os geradores com uma seed única
	randSource = rand.NewSource(time.Now().UnixNano())

	geradorCarPlate       = rand.New(randSource)
	geradorCarType        = rand.New(randSource)
	geradorCarColor       = rand.New(randSource)
	geradorCarBrand       = rand.New(randSource)
	geradorOwnerName      = rand.New(randSource)
	geradorOwnerSurName   = rand.New(randSource)
	geradorDate           = rand.New(randSource)
	geradorMaxSpeed       = rand.New(randSource)
	geradorAddress        = rand.New(randSource)
	geradorDirection      = rand.New(randSource)
	geradorStreetDirection = rand.New(randSource)
	geradorTime           = rand.New(randSource)
	geradorSpeed          = rand.New(randSource)
	geradorViolation      = rand.New(randSource)
	geradorPollution      = rand.New(randSource)
	geradorAcidente   	  = rand.New(randSource)
)


type CarInfo struct {
	CarPlate          string `json:"carPlate"`
	CarType           string `json:"carType"`
	CarColor          string `json:"carColor"`
	CarBrand          string `json:"carBrand"`
	VeiculeOwnerName  string `json:"veiculeOwnerName"`
	VehicleOwnerCPF   string `json:"veiculeOwneCPF"`
	Time              string `json:"time"`
	Date              string `json:"date"`
	Address           string `json:"address"`
	Speed             float64 `json:"speed"`
	MaxSpeed          int `json:"maxSpeed"`
	MaxSpeedGenerated int `json:"maxSpeedGenerated"`
	Direction         string `json:"direction"`
	StreetDirection   string `json:"streetDirection"`
	PollutionRate     float64 `json:"pollutionLevel"`
}



type TrafficInfo interface {
    Generate() string
}

type ViolationInfo struct {
	Violation string
}

func (vi *ViolationInfo) ViolationType() string {
	return vi.Violation
}

type InfractionInfo interface {
	ViolationType() string
}

type ViolationGenerator struct {
	randSource *rand.Rand
}


type Generator struct {
	randSource *rand.Rand
}

func NewGenerator() *Generator {
	randSource := rand.NewSource(time.Now().UnixNano())
	return &Generator{
		randSource: rand.New(randSource),
	}
}


// Definição da interface Acidente
type Acidente interface {
	TipoAcidente() string
	NivelSeveridade() int
	NumeroVitimas() int
	DataAcidente() string
	HoraAcidente() string
	
	
}

// Implementação da struct AcidenteInfo que implementa a interface Acidente
type AcidenteInfo struct {
	Tipo       string `json:"Tipo"`
	Severidade int    `json:"severidade"`
	Vitimas    int    `json:"vitimas"`
	Data       string `json:"data"`
	Hora       string `json:"hora"`
}

type CarPlateGenerator struct {
	*Generator
}

type VehicleOwnerCpfGenerator struct {
	*Generator
}


type VehicleOwnerNameGenerator struct {
	*Generator
}

type VehicleOwnerSurNameGenerator struct {
	*Generator
}

type DateGenerator struct {
	*Generator
}

type MaxSpeedGenerator struct {
	*Generator
}

type TimeGenerator struct {
	*Generator
}

type SpeedGenerator struct {
	*Generator
}

type AddressGenerator struct {
	*Generator
}

type DirectionGenerator struct {
	*Generator
}

type StreetDirectionGenerator struct {
	*Generator
}



type CarColorGenerator struct {
	*Generator
}

type CarBrandGenerator struct {
	*Generator
}

type CarTypeGenerator struct {
	*Generator
}

type PollutionGenerator struct {
	*Generator
}

type AcidenteGenerator struct {
	*Generator
}


func NewCarPlateGenerator() *CarPlateGenerator {
    generator := NewGenerator()  // Cria um novo gerador
    return &CarPlateGenerator{
        Generator: generator,  
    }
}


func NewVehicleOwnerCpfGenerator() *VehicleOwnerCpfGenerator {
	generator := NewGenerator() 
return &VehicleOwnerCpfGenerator{
	Generator: generator, 
}		
}

func NewVehicleOwnerNameGenerator() *VehicleOwnerNameGenerator {
	generator := NewGenerator()
	return &VehicleOwnerNameGenerator{
		Generator: generator,
	}

}

func NewVehicleOwnerSurNameGenerator() *VehicleOwnerSurNameGenerator {
	generator := NewGenerator()
	return &VehicleOwnerSurNameGenerator{
		Generator: generator,
	}
}

func NewDateGenerator() *DateGenerator {
	generator := NewGenerator()
	return &DateGenerator{
		Generator: generator,
	}
}

func NewMaxSpeedGenerator() *MaxSpeedGenerator {
	generator := NewGenerator()
	return &MaxSpeedGenerator{
		Generator: generator,
	}
}

func NewTimeGenerator() *TimeGenerator {
	generator := NewGenerator()
	return &TimeGenerator{
		Generator: generator,
	}
}

func NewSpeedGenerator() *SpeedGenerator {
	generator := NewGenerator()
	return &SpeedGenerator{
		Generator: generator,
	}
}

func NewAddressGenerator() *AddressGenerator {
	generator := NewGenerator()
	return &AddressGenerator{
		Generator: generator,
	}
}

func NewDirectionGenerator() *DirectionGenerator {
	generator := NewGenerator()
	return &DirectionGenerator{
		Generator: generator,
	}
}

func NewStreetDirectionGenerator() *StreetDirectionGenerator {
	generator := NewGenerator()
	return &StreetDirectionGenerator{
		Generator: generator,
	}
}

// func NewViolationGenerator() *ViolationGenerator {
// 	generator := NewGenerator()
// 	return &ViolationGenerator{
// 		Generator: generator,
// 	}
// }

func NewCarColorGenerator() *CarColorGenerator {
	generator := NewGenerator()
	return &CarColorGenerator{
		Generator: generator,
	}
}

func NewCarBrandGenerator() *CarBrandGenerator {
	generator := NewGenerator()
	return &CarBrandGenerator{
		Generator: generator,
	}

}

func NewCarTypeGenerator() *CarTypeGenerator {
	generator := NewGenerator()
	return &CarTypeGenerator{
		Generator: generator,
	}
}

func NewPollutionGenerator() *PollutionGenerator {
	generator := NewGenerator()
	return &PollutionGenerator{
		Generator: generator,
	}
}

func NewAcidenteGenerator() *AcidenteGenerator {
	generator := NewGenerator()
	return &AcidenteGenerator{
		Generator: generator,
	}
}




func (g *CarPlateGenerator) Generate() string {
   
    plate := ""
    for i := 0; i < 3; i++ {
        randomLetter := rune(g.randSource.Intn(26) + 'A')
        plate += string(randomLetter)
    }

    // Adiciona um traço
    plate += "-"

    // Gera quatro números aleatórios para a placa
    for i := 0; i < 4; i++ {
        randomDigit := g.randSource.Intn(10)
        plate += fmt.Sprintf("%d", randomDigit)
    }

    return plate
}






func (g *VehicleOwnerCpfGenerator) Generate() string {
	var cpf string
	for i := 0; i < 11; i++ {
		randomDigit := geradorCarPlate.Intn(10)
		cpf += fmt.Sprintf("%d", randomDigit)
	}

	return cpf

}



func (g *VehicleOwnerNameGenerator) Generate() string {
	randomIndex := geradorOwnerName.Intn(len(nomes))
	return nomes[randomIndex]
}

func (g *VehicleOwnerSurNameGenerator) Generate() string {
	randomIndex := geradorOwnerSurName.Intn(len(sobrenomes))
	return sobrenomes[randomIndex]
}

func (g *DateGenerator) Generate() string {
	year := geradorDate.Intn(20) + 2010
	month := geradorDate.Intn(12) + 1
	day := geradorDate.Intn(28) + 1
	if month == 2 && day > 28 {
		day = 28
	}

	if month == 4 || month == 6 || month == 9 || month == 11 {
		if day > 30 {
			day = 30
		}
	}

	return fmt.Sprintf("%d-%d-%d", year, month, day)
}

func (g *MaxSpeedGenerator) Generate() int {
	return geradorMaxSpeed.Intn(180) + 30
}

func (g *TimeGenerator) Generate() string {
	hour := geradorTime.Intn(24)
	minute := geradorTime.Intn(60)
	second := geradorTime.Intn(60)

	return fmt.Sprintf("%d:%d:%d", hour, minute, second)
}

func (g *SpeedGenerator) Generate() float64 {
	return geradorSpeed.Float64() * float64(new(MaxSpeedGenerator).Generate())
}

func (g *AddressGenerator) Generate() string {
	ruas := ruas[geradorAddress.Intn(len(ruas))]
	bairros := bairros[geradorAddress.Intn(len(bairros))]
	return fmt.Sprintf("%s, %s", ruas, bairros)
}

func (g *DirectionGenerator) Generate() string {
	return direcoes[geradorDirection.Intn(len(direcoes))]
}

func (g *StreetDirectionGenerator) Generate() string {
	return streetDirections[geradorStreetDirection.Intn(len(streetDirections))]
}





func (g *CarColorGenerator) Generate() string {
	var colors = []string{
		"red", "blue", "yellow", "black", "white", "silver", "gray", "green", "brown", "orange", "purple", "pink", "gold", "beige", "cream", "ivory", "teal", "turquoise", "lime", "olive", "maroon", "navy", "aquamarine", "mint", "apricot", "indigo", "crimson", "azure", "fuchsia", "plum", "lavender", "magenta", "salmon", "tan", "khaki", "orchid", "peridot", "cobalt", "cerulean", "rust", "vermilion", "copper", "peach", "rose", "chartreuse", "taupe", "golden", "coral", "cyan",
	}

	return colors[geradorCarColor.Intn(len(colors))]
}

func (g *CarBrandGenerator) Generate() string {
	return marcas[geradorCarBrand.Intn(len(marcas))]
}

func (g *CarTypeGenerator) Generate() string {
    // Obtenha uma marca de carro aleatória
    randomBrandIndex := g.randSource.Intn(len(marcas))
    brand := marcas[randomBrandIndex]

    // Obtenha um tipo de carro aleatório para a marca
    types := carTypes[brand]

    // Gere um índice aleatório para o tipo de carro
    randomTypeIndex := g.randSource.Intn(len(types))

    return types[randomTypeIndex]
}


func (g *PollutionGenerator) Generate() float64 {
	randomValue := geradorPollution.Float64()
	pollutionRate := randomValue * pollution[(&CarColorGenerator{}).Generate()]
	return pollutionRate
}

func (g *AcidenteGenerator) Generate() *AcidenteInfo {
	randomValue := rand.Float64()
	if randomValue < 0.1 {
		tipo := geraTipoAcidente()
		severidade := geraSeveridadeAcidente()
		data := (&DateGenerator{}).Generate()
		hora := (&TimeGenerator{}).Generate()
		vitimas := geraVitimas()

		return &AcidenteInfo{
			Tipo:       tipo.TipoAcidente(),
			Severidade: severidade.NivelSeveridade(),
			Vitimas:    vitimas,
			Data:       data,
			Hora:       hora,
		}
	} else {
		return &AcidenteInfo{}
	}
}

func (c *CarInfo) calculatePollution() {
	c.PollutionRate = geradorPollution.Float64() * pollution[c.CarBrand]
}






func CarFactory() *CarInfo {
	carPlate := NewCarPlateGenerator().Generate()
	carType := NewCarTypeGenerator().Generate()
	carColor := NewCarColorGenerator().Generate()
	carBrand := NewCarBrandGenerator().Generate()
	veiculeOwnerName := NewVehicleOwnerNameGenerator().Generate()
	vehicleOwnerCPF := NewVehicleOwnerCpfGenerator().Generate()
	time := NewTimeGenerator().Generate()
	date := NewDateGenerator().Generate()
	address := NewAddressGenerator().Generate()
	speed := NewSpeedGenerator().Generate()
	maxSpeed := NewMaxSpeedGenerator().Generate()
	maxSpeedGenerated := NewMaxSpeedGenerator().Generate()
	direction := NewDirectionGenerator().Generate()
	streetDirection := NewStreetDirectionGenerator().Generate()
	pollutionRate := NewPollutionGenerator().Generate()

	return &CarInfo{
		CarPlate:          carPlate,
		CarType:           carType,
		CarColor:          carColor,
		CarBrand:          carBrand,
		VeiculeOwnerName:  veiculeOwnerName,
		VehicleOwnerCPF:   vehicleOwnerCPF,
		Time:              time,
		Date:              date,
		Address:           address,
		Speed:             speed,
		MaxSpeed:          maxSpeed,
		MaxSpeedGenerated: maxSpeedGenerated,
		Direction:         direction,
		StreetDirection:   streetDirection,
		PollutionRate:     pollutionRate,
	}
}

func (g *ViolationGenerator) GenerateViolation() InfractionInfo {
	violationRate := 0.1
	if g.randSource.Float64() < violationRate {
		return nil // No violation
	}

	violations := []string{
		"Speeding",
		"Running a red light",
		"Running a stop sign",
		"Reckless driving",
		"Driving under the influence",
		"Texting while driving",
		"Careless driving",
		"Seat belt violation",
		"Wrong way driving",
		"Tailgating",
		"Failure to yield",
		"Driving without lights",
		"Failure to signal",
		"Driving too slow",
		"Driving without a license or with a suspended license",
		"Improper passing",
		"Failure to stop for a school bus",
		"Other",
	}

	randomIndex := g.randSource.Intn(len(violations))
	return &ViolationInfo{
		Violation: violations[randomIndex],
	}
}

func ViolationFactory() *ViolationInfo {
	generator := &ViolationGenerator{
		randSource: rand.New(rand.NewSource(time.Now().UnixNano())),
	}

	violation := generator.GenerateViolation()
	if violation == nil {
		return &ViolationInfo{}
	}

	return &ViolationInfo{
		Violation: violation.ViolationType(),
	}
}




func main() {
	config := kafka.NewKafkaConfiguration()
	producer, err := kafka.NewKafkaProducer(config)
	if err != nil {
		fmt.Printf("Error creating Kafka producer: %v\n", err)
		return
	}

	defer producer.Producer.Close()

	// Start a goroutine to generate information every 3 seconds
	go func() {
		for {
			trafficGen := NewTrafficGenerator()
			fmt.Printf("Traffic info: %+v\n", trafficGen) // Print the traffic info

			trafficJSON, err := json.Marshal(trafficGen) // Convert to JSON
			if err != nil {
				fmt.Printf("Error converting to JSON: %v\n", err)
				continue
			}

			// Send the traffic information to Kafka
			producer.SendMessage("traffic-topic", string(trafficJSON))
			// fmt.Printf("Sending message to Kafka: %s\n", string(trafficJSON))

			time.Sleep(3 * time.Second)
		}
	}()

	// Keep the program running indefinitely
	select {}
}






func (ai AcidenteInfo) TipoAcidente() string {
	return ai.Tipo
}

func (ai AcidenteInfo) NivelSeveridade() int {
	return ai.Severidade
}

func (ai AcidenteInfo) NumeroVitimas() int {
	return ai.Vitimas
}

func (ai AcidenteInfo) DataAcidente() string {
	return ai.Data
}

func (ai AcidenteInfo) HoraAcidente() string {
	return ai.Hora
}



func geraTipoAcidente() AcidenteInfo {
	acidenteRate := 0.1
	if rand.ExpFloat64() < acidenteRate {
		randomValue := rand.Intn(len(acidentes))
		return AcidenteInfo{
			Tipo:       acidentes[randomValue],
			Severidade: 0,
		}
	}
	return AcidenteInfo{}
}

// Função para gerar a severidade de um acidente
func geraSeveridadeAcidente() AcidenteInfo {
	gerador := rand.New(rand.NewSource(42))

	severidadeRates := []float64{0.3, 0.2, 0.2, 0.15, 0.15}
	for i, rate := range severidadeRates {
		if gerador.ExpFloat64() < rate {
			return AcidenteInfo{
				Tipo:       "",
				Severidade: i + 1,
			}
		}
	}
	return AcidenteInfo{}
}

// Função para gerar um acidente
// Função para gerar um acidente
func AcidenteFactory() *AcidenteInfo {
	randomValue := rand.Float64()
	if randomValue < 0.1 {
		tipo := geraTipoAcidente()
		severidade := geraSeveridadeAcidente()
		data := (&DateGenerator{}).Generate()
		hora := (&TimeGenerator{}).Generate()
		vitimas := geraVitimas()

		return &AcidenteInfo{
			Tipo:       tipo.TipoAcidente(),
			Severidade: severidade.NivelSeveridade(),
			Vitimas:    vitimas,
			Data:       data,
			Hora:       hora,
		}
	} else {
		return &AcidenteInfo{}
	}
}



func geraVitimas() int {
	gerador := rand.New(rand.NewSource(42))

	vitimasRate := []float64{0.25, 0.2, 0.13, 0.09, 0.07}
	for i, rate := range vitimasRate {
		if gerador.ExpFloat64() < rate {
			return i + 1
		}
	}

	
	return 0
}


type TrafficGenerator struct {
	Car       CarInfo       `json:"car"`
	Infraction ViolationInfo `json:"infraction"`
	Acidente  AcidenteInfo  `json:"acidente"`
}

func NewTrafficGenerator() *TrafficGenerator {
	return &TrafficGenerator{
		Car:       *CarFactory(),
		Acidente:  *AcidenteFactory(),
		Infraction: *ViolationFactory(),
	}
}
